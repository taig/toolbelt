package io.taig.android.monix

import android.app.PendingIntent
import android.app.PendingIntent.FLAG_UPDATE_CURRENT
import android.content.{ Context, Intent, IntentFilter }
import com.google.android.gms.location.ActivityRecognition.ActivityRecognitionApi
import com.google.android.gms.location.ActivityRecognitionResult
import io.taig.android.app.broadcast.Receiver
import io.taig.android.log.Log
import io.taig.android.monix.syntax.task._
import monix.eval.Task
import monix.execution.Ack.Stop
import monix.execution.Cancelable
import monix.reactive.observers.Subscriber

import scala.concurrent.duration._

object ActivityRecognition {
    def disconnect( client: GoogleApiClient, intent: PendingIntent )(
        implicit
        t: Log.Tag
    ): Unit =
        if ( client.isConnected ) {
            Log.d( "Unsubscribing from activity recognition updates" )
            ActivityRecognitionApi.removeActivityUpdates( client, intent )
        }

    class ActivityUpdate(
            subscriber: Subscriber.Sync[ActivityRecognitionResult],
            client:     GoogleApiClient,
            pending:    PendingIntent
    )(
            implicit
            t: Log.Tag
    ) extends Receiver {
        override def onReceive( intent: Intent )(
            implicit
            c: Context
        ): Unit = {
            val result = ActivityRecognitionResult.extractResult( intent )
            Log.d( s"Received activity recognition update: $result" )

            if ( subscriber.onNext( result ) == Stop ) {
                disconnect( client, pending )
            }
        }
    }

    def apply(
        client:     GoogleApiClient,
        interval:   FiniteDuration,
        subscriber: Subscriber.Sync[ActivityRecognitionResult]
    )(
        implicit
        c: Context,
        t: Log.Tag
    ): Cancelable = {
        import subscriber.scheduler

        val id = "io.taig.android.monix.ActivityUpdate"

        val intent = PendingIntent.getBroadcast(
            c,
            8437,
            new Intent( id ),
            FLAG_UPDATE_CURRENT
        )

        val receiver = new ActivityUpdate( subscriber, client, intent )
        val filter = new IntentFilter( id )
        c.registerReceiver( receiver, filter )

        Log.d( "Subscribing to activity recognition updates" )

        val result = ActivityRecognitionApi
            .requestActivityUpdates( client, interval.toSeconds, intent )

        val cancelable = Task.fromPendingResult( result ).foreach { status ⇒
            if ( !status.isSuccess ) {
                val message = "Activity recognition updates failed: " +
                    s"${status.getStatusMessage} (${status.getStatusCode})"
                val exception = new IllegalStateException( message )
                subscriber.onError( exception )
            }
        }

        Cancelable { () ⇒
            cancelable.cancel()
            disconnect( client, intent )
        }
    }
}