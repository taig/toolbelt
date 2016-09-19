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
            Observable.create( strategy ) { downstream ⇒
                val listener = new LocationListener {
                    override def onLocationChanged( location: Location ) = {
                        downstream.onNext( location )
                    }
                }

                Log.d( "Adding registering for location updates" )

                val pending = LocationServices.FusedLocationApi.requestLocationUpdates(
                    client,
                    request,
                    listener,
                    c.getMainLooper
                )

                val result = new ResultCallback[Status] {
                    override def onResult( status: Status ) = {
                        if ( !status.isSuccess ) {
                            downstream.onError {
                                new IllegalStateException( status.getStatusMessage )
                            }
                        }
                    }
                }

                pending.setResultCallback( result )

                Cancelable { () ⇒
                    if ( client.isConnected ) {
                        Log.d( "Removing registration for location updates" )

                        LocationServices.FusedLocationApi.removeLocationUpdates(
                            client,
                            listener
                        )
                    }
                }
            }
        case Suspended( _, _ ) ⇒ Observable.empty[Location]
    }
}

object observable {
    final class companion( observable: Observable.type ) {
        def fromGoogleApiClient(
            client:   GoogleApiClient,
            strategy: OverflowStrategy.Synchronous[GoogleApiClientEvent] = OverflowStrategy.Unbounded
        )(
            implicit
            t: Log.Tag
        ): Observable[GoogleApiClientEvent] = {
            Observable.create[GoogleApiClientEvent]( strategy ) { downstream ⇒
                client.registerConnectionCallbacks {
                    new ConnectionCallbacks {
                        override def onConnected( bundle: Bundle ) = {
                            Log.d( "Connection to GoogleApiClient established" )
                            downstream.onNext( Connected( client, bundle ) )
                        }

                        override def onConnectionSuspended( cause: Int ) = {
                            Log.d( "Connection to GoogleApiClient suspended" )
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

                Log.d( "Connecting to GoogleApiClient ..." )
                client.connect()

                Cancelable { () ⇒
                    Log.d( "Disconnecting from GoogleApiClient ..." )
                    client.disconnect()
                }
            }
        }
    }
}