package io.taig.android.extension

import io.taig.android.graphic.{ Color, Dimension, Point, operation }

import scala.language.{ implicitConversions, reflectiveCalls }

package object graphic {
    implicit class ToolbeltDimension[T: Integral]( val dimension: Dimension[T] )
        extends operation.Dimension[T]

    implicit class ToolbeltDimensioned[T: Numeric]( dimensioned: { def getWidth(): T; def getHeight(): T } ) {
        def dimension = Dimension( dimensioned.getWidth(), dimensioned.getHeight() )
    }

    implicit class ToolbeltIntrinsicDimensioned[T: Numeric]( dimensioned: { def getIntrinsicWidth(): T; def getIntrinsicHeight(): T } ) {
        def dimension = Dimension( dimensioned.getIntrinsicWidth(), dimensioned.getIntrinsicHeight() )
    }

    implicit class ToolbeltPointed[T: Numeric]( pointed: { def getX(): Int; def getY(): Int } ) {
        def position = Point( pointed.getX(), pointed.getY() )
    }

    implicit class ToolbeltNumeric[T: Numeric]( a: T ) {
        def x( b: T ) = Dimension( a, b )
    }

    implicit def `Color -> Int`( color: Color ): Int = color.color

    implicit def `Int -> Color`( color: Int ): Color = Color( color )

    implicit def `String -> Color`( hex: String ): Color = Color( hex )
}