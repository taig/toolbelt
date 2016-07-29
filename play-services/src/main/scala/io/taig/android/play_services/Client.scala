package io.taig.android.play_services

import android.os.Bundle
import com.google.android.gms.common.api.GoogleApiClient

object Client {
    sealed trait Event

    object Event {
        final case class Suspended( cause: Int ) extends Event
        final case class Connected( client: GoogleApiClient, bundle: Bundle ) extends Event
    }
}