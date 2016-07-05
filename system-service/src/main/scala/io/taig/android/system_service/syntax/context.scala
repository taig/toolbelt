package io.taig.android.system_service.syntax

import android.content.Context
import io.taig.android.system_service.operation

import scala.language.implicitConversions

trait context {
    implicit def contextSyntax( context: Context ): operation.context = {
        new operation.context( context )
    }
}

object context extends context