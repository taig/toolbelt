package io.taig.android.util.syntax

import io.taig.android.util.operation

import scala.language.implicitConversions

trait `class` {
    implicit def utilClassSyntax( `class`: Class[_] ): operation.`class` = {
        new operation.`class`( `class` )
    }
}

object `class` extends `class`