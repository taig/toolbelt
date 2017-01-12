package io.taig.android.monix.operation

import java.io.IOException
import java.util.UUID

import android.app.FragmentManager
import android.content.Context
import android.location.Location
import android.os.Bundle
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient.{ ConnectionCallbacks, OnConnectionFailedListener }
import com.google.android.gms.common.api.{ GoogleApiClient, ResultCallback, Status }
import com.google.android.gms.location.{ LocationListener, LocationRequest, LocationServices }
import io.taig.android.monix._
import io.taig.android.monix.GoogleApiClientEvent.{ Connected, Suspended }
import monix.execution.Cancelable
import monix.reactive.{ Observable, OverflowStrategy }
import rx.RxReactiveStreams

final class observable[+T]( observable: Observable[T] )

final class observableGoogleApiClientEvent( observable: Observable[GoogleApiClientEvent] ) {
    def locationUpdates(
        request:  LocationRequest,
        strategy: OverflowStrategy.Synchronous[Location] = OverflowStrategy.Unbounded
    )(
        implicit
        c: Context
    ): Observable[Location] = observable.flatMap {
        case Connected( client, _ ) ⇒
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
        def fromRx[T]( observable: rx.Observable[T] ): Observable[T] = {
            val publisher = RxReactiveStreams.toPublisher( observable )
            Observable.fromReactivePublisher( publisher )
        }

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

        def fromReactiveDialogFragment( dialog: app.fragment.Reactive )(
            manager:  FragmentManager,
            tag:      Option[String]                                            = None,
            strategy: OverflowStrategy.Synchronous[app.fragment.Reactive.Event] = OverflowStrategy.Unbounded
        ): Observable[app.fragment.Reactive.Event] = {
            Observable.create( strategy ) { downstream ⇒
                val helper = app.fragment.Reactive.Helper(
                    downstream,
                    dialog,
                    manager,
                    tag.getOrElse( UUID.randomUUID().toString )
                )

                manager
                    .beginTransaction()
                    .add( helper, s"${helper.tag}-helper" )
                    .commit()

                Cancelable.empty
            }
        }
    }
}