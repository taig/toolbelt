package io.taig.android.monix

import android.content.Context
import android.location.Location
import com.google.android.gms.location.{ LocationListener, LocationRequest, LocationServices }
import io.taig.android.log.Log
import io.taig.android.monix.syntax.task._
import monix.eval.Task
import monix.execution.Cancelable
import monix.reactive.observers.Subscriber

object LocationUpdates {
    def apply(
        client:     GoogleApiClient,
        request:    LocationRequest,
        subscriber: Subscriber.Sync[Location]
    )(
        implicit
        c: Context,
        t: Log.Tag
    ): Cancelable = {
        import subscriber.scheduler

        val listener = new LocationListener {
            override def onLocationChanged( location: Location ): Unit = {
                Log.d( s"Received location update: $location" )
                subscriber.onNext( location )
            }
        }

        Log.d( "Subscribing to location updates" )

        val pending = LocationServices.FusedLocationApi.requestLocationUpdates(
            client,
            request,
            listener,
            c.getMainLooper
        )

        val result = Task.fromPendingResult( pending ).foreach { status ⇒
            if ( !status.isSuccess ) {
                val message = "Location updates failed: " +
                    s"${status.getStatusMessage} (${status.getStatusCode})"
                val exception = new IllegalStateException( message )
                subscriber.onError( exception )
            }
        }

        Cancelable { () ⇒
            Log.d( "Unsubscribing from location updates" )
            result.cancel()
            LocationServices.FusedLocationApi
                .removeLocationUpdates( client, listener )
        }
    }
}