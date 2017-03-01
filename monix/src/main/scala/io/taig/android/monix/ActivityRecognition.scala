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
import monix.execution.Cancelable
import monix.reactive.observers.Subscriber

import scala.concurrent.duration._

object ActivityRecognition {
    val Filter = "io.taig.android.monix.ActivityUpdate"

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

        class ActivityUpdate extends Receiver {
            override def onReceive( intent: Intent )(
                implicit
                c: Context
            ): Unit = {
                val result = ActivityRecognitionResult.extractResult( intent )
                Log.d( s"Received activity recognition update: $result" )
                subscriber.onNext( result )
            }
        }

        val filter = new IntentFilter( Filter )
        val receiver = new ActivityUpdate
        c.registerReceiver( receiver, filter )

        val intent = PendingIntent.getBroadcast(
            c,
            8437,
            new Intent( Filter ),
            FLAG_UPDATE_CURRENT
        )

        Log.d( "Subscribing to activity recognition updates" )

        val result = ActivityRecognitionApi
            .requestActivityUpdates( client, interval.toSeconds, intent )

        val pending = Task.fromPendingResult( result ).foreach { status ⇒
            if ( !status.isSuccess ) {
                val message = "Activity recognition updates failed: " +
                    s"${status.getStatusMessage} (${status.getStatusCode})"
                val exception = new IllegalStateException( message )
                subscriber.onError( exception )
            }
        }

        Cancelable { () ⇒
            Log.d( "Unsubscribing from activity recognition updates" )
            pending.cancel()
            ActivityRecognitionApi.removeActivityUpdates( client, intent )
        }
    }
}