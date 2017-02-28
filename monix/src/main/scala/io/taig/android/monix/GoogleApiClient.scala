package io.taig.android.monix

import java.io.IOException

import android.os.Bundle
import com.google.android.gms
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient.{ ConnectionCallbacks, OnConnectionFailedListener }
import io.taig.android.log.Log
import io.taig.android.monix.GoogleApiClient.Event.{ Connected, Suspended }
import monix.execution.Cancelable
import monix.reactive.observers.Subscriber

object GoogleApiClient {
    sealed trait Event {
        def client: GoogleApiClient
    }

    object Event {
        def unapply( event: Event ): Option[GoogleApiClient] = {
            Some( event.client )
        }

        final case class Connected(
            client: GoogleApiClient,
            bundle: Bundle
        ) extends Event

        final case class Suspended(
            client: GoogleApiClient,
            cause:  Int
        ) extends Event
    }

    def apply(
        client:     gms.common.api.GoogleApiClient,
        subscriber: Subscriber.Sync[Event]
    )(
        implicit
        t: Log.Tag
    ): Cancelable = {
        client.registerConnectionCallbacks {
            new ConnectionCallbacks {
                override def onConnected( bundle: Bundle ) = {
                    Log.d( "Connection to GoogleApiClient established" )
                    subscriber.onNext( Connected( client, bundle ) )
                }

                override def onConnectionSuspended( cause: Int ) = {
                    Log.d( "Connection to GoogleApiClient suspended" )
                    subscriber.onNext( Suspended( client, cause ) )
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

                    subscriber.onError( exception )
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