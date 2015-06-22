package io.taig.android

import scala.language.{implicitConversions, reflectiveCalls}

package object graphic
{
	implicit class	RichDimension[T: Integral]( val dimension: Dimension[T] )
	extends			ops.Dimension[T]

	implicit class RichDimensioned[T: Numeric]( dimensioned: { def getWidth: T; def getHeight: T } )
	{
		def dimension = Dimension( dimensioned.getWidth, dimensioned.getHeight )
	}

	implicit class RichIntrinsicDimensioned[T: Numeric]( dimensioned: { def getIntrinsicWidth: T; def getIntrinsicHeight: T } )
	{
		def dimension = Dimension( dimensioned.getIntrinsicWidth, dimensioned.getIntrinsicHeight )
	}

	implicit class RichPointed[T: Numeric]( pointed: { def getX: Int; def getY: Int } )
	{
		def position = Point( pointed.getX, pointed.getY )
	}

	implicit class RichNumeric[T: Numeric]( a: T )
	{
		def x( b: T ) = Dimension( a, b )
	}

	implicit def `Color -> Int`( color: graphic.Color ): Int = color.color

	implicit def `Int -> Color`( color: Int ): graphic.Color = Color( color )

	implicit def `String -> Color`( hex: String ): graphic.Color = Color( hex )
}