package io.taig.android.graphic.syntax

import android.graphics.drawable.Drawable
import io.taig.android.graphic.operation

import scala.language.implicitConversions

trait drawable {
    implicit def graphicDrawableSyntax(
        drawable: Drawable
    ): operation.drawable = new operation.drawable( drawable )
}

object drawable extends drawable