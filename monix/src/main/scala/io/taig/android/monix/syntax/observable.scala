package io.taig.android.monix.syntax

import io.taig.android.monix.{GoogleApiClient, operation}
import monix.reactive.Observable

import scala.language.implicitConversions

trait observable {
  implicit def monixObservableSyntax[T](
      observable: Observable[T]
  ): operation.observable[T] = {
    new operation.observable[T](observable)
  }

  implicit def monixObservableCompanionSyntax(
      observable: Observable.type
  ): operation.observable.companion = {
    new operation.observable.companion(observable)
  }

  implicit def monixObservableGoogleApiClientEvent(
      observable: Observable[GoogleApiClient.Event]
  ): operation.observableGoogleApiClientEvent = {
    new operation.observableGoogleApiClientEvent(observable)
  }
}

object observable extends observable
