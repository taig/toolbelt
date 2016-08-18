package io.taig.android.play_services

import android.os.Bundle

object GoogleApiClient {
    sealed trait Event

    object Event {
        final case class Connected( client: GoogleApiClient, bundle: Bundle ) extends Event
        final case class Suspended( client: GoogleApiClient, cause: Int ) extends Event
    }
}