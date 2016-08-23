package io.taig.android.monix

import android.os.Bundle
import com.google.android.gms.common.api.GoogleApiClient

sealed trait GoogleApiClientEvent

object GoogleApiClientEvent {
    final case class Connected(
        client: GoogleApiClient,
        bundle: Bundle
    ) extends GoogleApiClientEvent

    final case class Suspended(
        client: GoogleApiClient,
        cause:  Int
    ) extends GoogleApiClientEvent
}