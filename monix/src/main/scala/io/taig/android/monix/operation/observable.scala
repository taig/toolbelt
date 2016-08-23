package io.taig.android.monix.operation

import java.io.IOException

import android.content.Context
import android.location.Location
import android.os.Bundle
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient.{ ConnectionCallbacks, OnConnectionFailedListener }
import com.google.android.gms.common.api.{ GoogleApiClient, ResultCallback, Status }
import com.google.android.gms.location.{ LocationListener, LocationRequest, LocationServices }
import io.taig.android.log.Log
import io.taig.android.monix.GoogleApiClientEvent
import io.taig.android.monix.GoogleApiClientEvent.{ Connected, Suspended }
import monix.execution.Cancelable
import monix.reactive.{ Observable, OverflowStrategy }

final class observable[+T]( observable: Observable[T] )

final class observableGoogleApiClientEvent( observable: Observable[GoogleApiClientEvent] ) {
    def locationUpdates(
        request:  LocationRequest,
        strategy: OverflowStrategy.Synchronous[Location] = OverflowStrategy.Unbounded
    )(
        implicit
        c: Context,
        t: Log.Tag
    ): Observable[Location] = observable.flatMap {
        case Connected( client, _ ) ⇒
            Log.d {
                "Connecting GoogleApiClient succeeded, requesting " +
                    "location updates"
            }

            Observable.create( strategy ) { downstream ⇒
                val listener = new LocationListener {
                    override def onLocationChanged( location: Location ) = {
                        downstream.onNext( location )
                    }
                }

                val pending = LocationServices.FusedLocationApi.requestLocationUpdates(
                    client,
                    request,
                    listener,
                    c.getMainLooper
                )

                val resultCallback = new ResultCallback[Status] {
                    override def onResult( status: Status ) = {
                        if ( !status.isSuccess ) {
                            downstream.onError {
                                new IllegalStateException( status.getStatusMessage )
                            }
                        }
                    }
                }

                pending.setResultCallback( resultCallback )

                Cancelable { () ⇒
                    LocationServices.FusedLocationApi.removeLocationUpdates(
                        client,
                        listener
                    )

                    client.disconnect()
                }
            }

        case Suspended( client, code ) ⇒
            Log.d {
                s"Connection to GoogleApiClient suspended ($code), " +
                    "reconnecting"
            }

            Observable.empty[Location]
    }
}

object observable {
    final class companion( observable: Observable.type ) {
        def fromGoogleApiClient(
            client:   GoogleApiClient,
            strategy: OverflowStrategy.Synchronous[GoogleApiClientEvent] = OverflowStrategy.Unbounded
        ): Observable[GoogleApiClientEvent] = {
            Observable.create[GoogleApiClientEvent]( strategy ) { downstream ⇒
                client.registerConnectionCallbacks {
                    new ConnectionCallbacks {
                        override def onConnected( bundle: Bundle ) = {
                            downstream.onNext( Connected( client, bundle ) )
                        }

                        override def onConnectionSuspended( cause: Int ) = {
                            downstream.onNext( Suspended( client, cause ) )
                        }
                    }
                }

                client.registerConnectionFailedListener {
                    new OnConnectionFailedListener {
                        override def onConnectionFailed( result: ConnectionResult ) = {
                            val message = s"${result.getErrorMessage} (${result.getErrorCode})"
                            downstream.onError( new IOException( message ) )
                        }
                    }
                }

                client.connect()

                Cancelable( client.disconnect )
            }
        }
    }
}