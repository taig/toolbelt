package io.taig.android.graphic.syntax

import io.taig.android.graphic.operation

import scala.language.implicitConversions

trait numeric {
  implicit def graphicNumericSyntax[T: Numeric](
      value: T
  ): operation.numeric[T] = new operation.numeric[T](value)
}

object numeric extends numeric
