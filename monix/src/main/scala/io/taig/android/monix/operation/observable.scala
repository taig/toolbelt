package io.taig.android.monix.operation

import java.util.UUID

import android.app.FragmentManager
import android.content.Context
import android.location.Location
import com.google.android.gms
import com.google.android.gms.common.api.{ PendingResult, ResultCallback, Status }
import com.google.android.gms.location.{ ActivityRecognitionResult, LocationListener, LocationRequest, LocationServices }
import io.reactivex.BackpressureStrategy
import io.taig.android.log.Log
import io.taig.android.monix.GoogleApiClient.Event.{ Connected, Suspended }
import io.taig.android.monix._
import monix.execution.Cancelable
import monix.execution.cancelables.{ CompositeCancelable, MultiAssignmentCancelable }
import monix.reactive.{ Observable, OverflowStrategy }
import rx.RxReactiveStreams
import io.{ reactivex ⇒ rx2 }

import scala.concurrent.duration._

final class observable[+T]( observable: Observable[T] )

final class observableGoogleApiClientEvent(
        observable: Observable[GoogleApiClient.Event]
) {
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

        def subscribe(
            client: gms.common.api.GoogleApiClient
        ): PendingResult[Status] = {
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

        def unsubscribe( client: gms.common.api.GoogleApiClient ): Unit = {
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

        def fromRx2[T](
            observable: rx2.Observable[T],
            strategy:   BackpressureStrategy = BackpressureStrategy.MISSING
        ): Observable[T] = {
            val publisher = observable.toFlowable( strategy )
            Observable.fromReactivePublisher( publisher )
        }

        def fromEventSink[T]( sink: EventSink[T] ): Observable[T] = {
            Observable.create( OverflowStrategy.Unbounded ) { subscriber ⇒
                val listener: T ⇒ Unit = subscriber.onNext

                sink.register( listener )

                Cancelable { () ⇒
                    sink.unregister( listener )
                }
            }
        }

        def fromGoogleApiClient(
            client:   GoogleApiClient,
            strategy: OverflowStrategy.Synchronous[GoogleApiClient.Event] = OverflowStrategy.Unbounded
        )(
            implicit
            t: Log.Tag
        ): Observable[GoogleApiClient.Event] =
            Observable.create[GoogleApiClient.Event]( strategy ) {
                GoogleApiClient( client, _ )
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

        def locationUpdates(
            client:   GoogleApiClient,
            request:  LocationRequest,
            strategy: OverflowStrategy.Synchronous[Location] = OverflowStrategy.Unbounded
        )(
            implicit
            c: Context,
            t: Log.Tag
        ): Observable[Location] =
            Observable.create( strategy ) {
                LocationUpdates( client, request, _ )
            }

        def fromReactiveDialogFragment( dialog: app.fragment.Reactive )(
            manager:  FragmentManager,
            tag:      Option[String]                                            = None,
            strategy: OverflowStrategy.Synchronous[app.fragment.Reactive.Event] = OverflowStrategy.Unbounded
        ): Observable[app.fragment.Reactive.Event] =
            Observable.create( strategy ) { scheduler ⇒
                val helper = app.fragment.Reactive.Helper(
                    scheduler,
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