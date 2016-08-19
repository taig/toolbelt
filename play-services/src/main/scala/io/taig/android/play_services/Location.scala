package io.taig.android.play_services

import android.content.Context
import android.location.{ Location ⇒ ALocation }
import com.google.android.gms.common.api.{ ResultCallback, Status }
import com.google.android.gms.location.{ LocationListener, LocationRequest, LocationServices }
import io.taig.android.log.Log
import monix.execution.Cancelable
import monix.reactive.{ Observable, OverflowStrategy }

object Location {
    def fromObservable(
        client:     Observable[GoogleApiClient.Event],
        parameters: LocationRequest,
        strategy:   OverflowStrategy.Synchronous[ALocation] = OverflowStrategy.Unbounded
    )(
        implicit
        c: Context,
        t: Log.Tag
    ): Observable[ALocation] = {
        client.flatMap {
            case GoogleApiClient.Event.Connected( client, _ ) ⇒
                Log.d {
                    "Connecting GoogleApiClient succeeded, requesting " +
                        "location updates"
                }

                fromClient( client, parameters, strategy )
            case GoogleApiClient.Event.Suspended( client, code ) ⇒
                Log.d {
                    s"Connection to GoogleApiClient suspended ($code), " +
                        "reconnecting"
                }

                Observable.empty[ALocation]
        }
    }

    def fromClient(
        client:     GoogleApiClient,
        parameters: LocationRequest,
        strategy:   OverflowStrategy.Synchronous[ALocation] = OverflowStrategy.Unbounded
    )(
        implicit
        c: Context
    ): Observable[ALocation] = Observable.create( strategy ) { downstream ⇒
        val listener = new LocationListener {
            override def onLocationChanged( location: ALocation ) = {
                downstream.onNext( location )
            }
        }

        val pending = LocationServices.FusedLocationApi.requestLocationUpdates(
            client,
            parameters,
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
}