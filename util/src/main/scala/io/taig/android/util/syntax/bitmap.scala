package io.taig.android.util.syntax

import android.graphics.Bitmap
import io.taig.android.util.operation

import scala.language.implicitConversions

trait bitmap {
  implicit def utilBitmapSyntax(bitmap: Bitmap): operation.bitmap = {
    new operation.bitmap(bitmap)
  }
}

object bitmap extends bitmap
