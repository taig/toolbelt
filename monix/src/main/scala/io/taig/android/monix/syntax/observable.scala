package io.taig.android.monix.syntax

import io.taig.android.monix.{ GoogleApiClientEvent, operation }
import monix.reactive.Observable

import scala.language.implicitConversions

trait observable {
    implicit def monixObservableSyntax[T](
        observable: Observable[T]
    ): operation.observable[T] = {
        new operation.observable[T]( observable )
    }

    implicit def monixObservableCompanionSyntax(
        observable: Observable.type
    ): operation.observable.companion = {
        new operation.observable.companion( observable )
    }

    implicit def monixObservableGoogleApiClientEvent(
        observable: Observable[GoogleApiClientEvent]
    ): operation.observable[GoogleApiClientEvent] = {
        new operation.observable[GoogleApiClientEvent]( observable )
    }
}

object observable extends observable