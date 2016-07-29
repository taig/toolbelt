package io.taig.android.play_services.operation

import java.io.IOException

import android.os.Bundle
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient._
import com.google.android.gms.common.api._
import io.taig.android.log.Log
import io.taig.android.play_services.Client.Event
import monix.eval.Task
import monix.execution.Cancelable
import monix.reactive.{ Observable, OverflowStrategy }

final class googleApiClient( client: GoogleApiClient ) {
    implicit val tag = Log.Tag[GoogleApiClient]

    def toObservable: Observable[Event] = {
        Observable.create[Event]( OverflowStrategy.Unbounded ) { downstream ⇒
            client.registerConnectionCallbacks {
                new ConnectionCallbacks {
                    override def onConnectionSuspended( cause: Int ) = {
                        downstream.onNext( Event.Suspended( cause ) )
                    }

                    override def onConnected( bundle: Bundle ) = {
                        downstream.onNext( Event.Connected( client, bundle ) )
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

            Cancelable( () ⇒ client.disconnect() )
        }
    }
}