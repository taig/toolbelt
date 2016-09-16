package io.taig.android.monix

import android.os.Bundle
import com.google.android.gms.common.api.GoogleApiClient

sealed trait GoogleApiClientEvent {
    def client: GoogleApiClient
}

object GoogleApiClientEvent {
    def unapply( event: GoogleApiClientEvent ): Option[GoogleApiClient] = {
        Some( event.client )
    }

    final case class Connected(
        client: GoogleApiClient,
        bundle: Bundle
    ) extends GoogleApiClientEvent

    final case class Suspended(
        client: GoogleApiClient,
        cause:  Int
    ) extends GoogleApiClientEvent
}