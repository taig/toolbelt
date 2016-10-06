package io.taig.android.graphic.syntax

import android.graphics.drawable.Drawable
import io.taig.android.graphic.operation

import scala.language.{ implicitConversions, reflectiveCalls }

trait drawable {
    implicit def drawableSyntax( drawable: Drawable ): operation.drawable = {
        new operation.drawable( drawable )
    }

    implicit def drawableCompanionSyntax(
        drawable: Drawable.type
    ): operation.drawable.companion = {
        new operation.drawable.companion( drawable )
    }
}

object drawable extends drawable