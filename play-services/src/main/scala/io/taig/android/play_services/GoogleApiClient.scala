package io.taig.android.play_services

import android.content.Context
import android.os.Bundle

object GoogleApiClient {
    def Builder( implicit c: Context ) = {
        new com.google.android.gms.common.api.GoogleApiClient.Builder( c )
    } 

    sealed trait Event

    object Event {
        final case class Connected( client: GoogleApiClient, bundle: Bundle ) extends Event
        final case class Suspended( client: GoogleApiClient, cause: Int ) extends Event
    }
}