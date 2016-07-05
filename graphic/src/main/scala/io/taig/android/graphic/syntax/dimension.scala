package io.taig.android.graphic.syntax

import io.taig.android.graphic.{ Dimension, operation }

import scala.language.{ implicitConversions, reflectiveCalls }

trait dimension {
    implicit def dimensionSyntax[T: Integral]( dimension: Dimension[T] ): operation.dimension[T] = {
        new operation.dimension[T]( dimension )
    }
}

object dimension extends dimension