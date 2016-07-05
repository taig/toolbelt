package io.taig.android.util.syntax

import io.taig.android.util.operation

import scala.language.implicitConversions

trait string {
    implicit def utilStringSyntax( string: String ): operation.string = {
        new operation.string( string )
    }
}

object string extends string