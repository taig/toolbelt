package io.taig.android.monix

import android.content.Context
import android.location.Location
import com.google.android.gms.location.{ LocationListener, LocationRequest, LocationServices }
import io.taig.android.log.Log
import io.taig.android.monix.syntax.task._
import monix.eval.Task
import monix.execution.Ack.Stop
import monix.execution.Cancelable
import monix.reactive.observers.Subscriber

object LocationUpdates {
    def unsubscribe( client: GoogleApiClient, listener: LocationListener )(
        implicit
        t: Log.Tag
    ): Unit =
        if ( client.isConnected ) {
            Log.d( "Unsubscribing from location updates" )

            LocationServices.FusedLocationApi
                .removeLocationUpdates( client, listener )
        }

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

                if ( subscriber.onNext( location ) == Stop ) {
                    unsubscribe( client, this )
                }
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
            result.cancel()
            unsubscribe( client, listener )
        }
    }
}