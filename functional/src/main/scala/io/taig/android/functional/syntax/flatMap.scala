package io.taig.android.functional.syntax

import cats.FlatMap
import io.taig.android.functional.operation

import scala.language.{higherKinds, implicitConversions}

trait flatMap {
  implicit def flatMapSyntax[A, F[_]: FlatMap](
      flatMap: F[A]): operation.flatMap[A, F] = {
    new operation.flatMap[A, F](flatMap)
  }
}

object flatMap extends flatMap
