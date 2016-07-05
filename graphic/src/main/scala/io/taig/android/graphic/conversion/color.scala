package io.taig.android.graphic.conversion

import io.taig.android.graphic.Color

import scala.language.implicitConversions

trait color {
    implicit def colorToInt( color: Color ): Int = color.color
}

object color extends color