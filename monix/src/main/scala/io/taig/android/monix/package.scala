package io.taig.android

import java.io.IOException

import android.os.Bundle
import com.google.android.gms
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient.{ ConnectionCallbacks, OnConnectionFailedListener }
import io.taig.android.log.Log
import io.taig.android.monix.GoogleApiClient.Event.{ Connected, Suspended }
import _root_.monix.execution.Ack.Stop
import _root_.monix.execution.Cancelable
import _root_.monix.reactive.observers.Subscriber

package object monix {
    type GoogleApiClient = com.google.android.gms.common.api.GoogleApiClient

    object GoogleApiClient {
        type Builder = com.google.android.gms.common.api.GoogleApiClient.Builder

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

        def disconnect( client: GoogleApiClient )(
            implicit
            t: Log.Tag
        ): Unit =
            if ( client.isConnecting || client.isConnected ) {
                Log.d( "Disconnecting from GoogleApiClient" )
                client.disconnect()
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
                    override def onConnected( bundle: Bundle ): Unit = {
                        Log.d( "Connection to GoogleApiClient established" )

                        val event = Connected( client, bundle )
                        if ( subscriber.onNext( event ) == Stop ) {
                            disconnect( client )
                        }
                    }

                    override def onConnectionSuspended( cause: Int ): Unit = {
                        Log.d( "Connection to GoogleApiClient suspended" )

                        val event = Suspended( client, cause )
                        if ( subscriber.onNext( event ) == Stop ) {
                            disconnect( client )
                        }
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
                disconnect( client )
            }
        }
    }
}