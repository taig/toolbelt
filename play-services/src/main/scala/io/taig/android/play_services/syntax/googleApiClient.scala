package io.taig.android.play_services.syntax

import com.google.android.gms.common.api.GoogleApiClient
import io.taig.android.play_services.operation

import scala.language.implicitConversions

trait googleApiClient {
    implicit def playServicesGoogleApiClientSyntax(
        client: GoogleApiClient
    ): operation.googleApiClient = new operation.googleApiClient( client )
}

object googleApiClient extends googleApiClient