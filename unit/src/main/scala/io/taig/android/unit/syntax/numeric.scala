package io.taig.android.unit.syntax

import io.taig.android.unit.operation

import scala.language.implicitConversions

trait unit {
    implicit def unitSyntax[T: Numeric]( value: T ): operation.numeric[T] = {
        new operation.numeric[T]( value )
    }
}

object unit extends unit