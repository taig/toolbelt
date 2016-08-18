package io.taig.android

import android.content.Context
import android.os.Bundle
import com.google.android.gms.common.api.{ GoogleApiClient ⇒ GClient }

package object play_services {
    type GoogleApiClient = GClient

    object GoogleApiClient {
        def Builder( implicit c: Context ) = {
            new GClient.Builder( c )
        }

        sealed trait Event

        object Event {
            final case class Connected( client: GoogleApiClient, bundle: Bundle ) extends Event
            final case class Suspended( client: GoogleApiClient, cause: Int ) extends Event
        }
    }
}