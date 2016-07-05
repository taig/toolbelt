package io.taig.android.widget.syntax

import android.view.View
import io.taig.android.widget.operation

import scala.language.implicitConversions

trait view {
    implicit def viewSyntax( view: View ): operation.view = {
        new operation.view( view )
    }
}

object view extends view