package io.taig.android.monix.operation

import java.io.IOException
import java.util.UUID

import android.app.FragmentManager
import android.content.Context
import android.location.Location
import android.os.Bundle
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient.{ ConnectionCallbacks, OnConnectionFailedListener }
import com.google.android.gms.common.api.{ GoogleApiClient, PendingResult, ResultCallback, Status }
import com.google.android.gms.location.{ ActivityRecognitionResult, LocationListener, LocationRequest, LocationServices }
import io.taig.android.log.Log
import io.taig.android.monix.GoogleApiClientEvent.{ Connected, Suspended }
import io.taig.android.monix._
import monix.execution.Cancelable
import monix.execution.cancelables.{ CompositeCancelable, MultiAssignmentCancelable }
import monix.reactive.{ Observable, OverflowStrategy }
import rx.RxReactiveStreams

import scala.concurrent.duration._

final class observable[+T]( observable: Observable[T] )

final class observableGoogleApiClientEvent( observable: Observable[GoogleApiClientEvent] ) {
    def locationUpdates(
        request:  LocationRequest,
        strategy: OverflowStrategy.Synchronous[Location] = OverflowStrategy.Unbounded
    )(
        implicit
        c: Context,
        t: Log.Tag
    ): Observable[Location] = Observable.create[Location]( strategy ) { downstream ⇒
        import downstream.scheduler

        val connection = MultiAssignmentCancelable()
        val cancelable = CompositeCancelable( connection )

        val listener = new LocationListener {
            override def onLocationChanged( location: Location ): Unit = {
                Log.d( s"Received location update: $location" )
                downstream.onNext( location )
            }
        }

        def subscribe( client: GoogleApiClient ): PendingResult[Status] = {
            Log.d( "Subscribing to location updates" )

            val pending = LocationServices.FusedLocationApi.requestLocationUpdates(
                client,
                request,
                listener,
                c.getMainLooper
            )

            val result = new ResultCallback[Status] {
                override def onResult( status: Status ) = {
                    if ( !status.isSuccess ) {
                        val message = status.getStatusMessage
                        val exception = new IllegalStateException( message )
                        downstream.onError( exception )
                    }
                }
            }

            pending.setResultCallback( result )
            pending
        }

        def unsubscribe( client: GoogleApiClient ): Unit = {
            if ( client.isConnected ) {
                Log.d( "Unsubscribing from location updates" )

                LocationServices.FusedLocationApi.removeLocationUpdates(
                    client,
                    listener
                )
            } else {
                Log.d {
                    "Not unsubscribing from location updates, " +
                        "because GoogleApiClient is not connected"
                }
            }
        }

        cancelable += observable.foreach {
            case Connected( client, _ ) ⇒
                val pending = subscribe( client )

                connection := Cancelable { () ⇒
                    pending.cancel()
                    unsubscribe( client )
                }
            case Suspended( client, _ ) ⇒
                connection := Cancelable( () ⇒ unsubscribe( client ) )
        }

        cancelable
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
                            val message = result.getErrorMessage +
                                s" (${result.getErrorCode})"
                            val exception = new IOException( message )

                            Log.d(
                                "Connection to GoogleApiClient failed",
                                exception
                            )

                            downstream.onError( exception )
                        }
                    }
                }

                Log.d( "Connecting to GoogleApiClient" )

                client.connect()

                Cancelable { () ⇒
                    Log.d( "Disconnecting from GoogleApiClient" )
                    client.disconnect()
                }
            }
        }

        def activityRecognition(
            client:   GoogleApiClient,
            interval: FiniteDuration                                          = 10.seconds,
            strategy: OverflowStrategy.Synchronous[ActivityRecognitionResult] = OverflowStrategy.Unbounded
        )(
            implicit
            c: Context,
            t: Log.Tag
        ): Observable[ActivityRecognitionResult] =
            Observable.create( strategy ) {
                ActivityRecognition( client, interval, _ )
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