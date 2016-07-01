package io.taig.android.context.syntax

import android.content.Context
import io.taig.android.context.operation

import scala.language.implicitConversions

trait context {
    implicit def contextSyntax( context: Context ): operation.context = {
        new operation.context( context )
    }
}

object context extends context