package io.taig.android

import io.taig.android.graphic.alignable.Value._
import io.taig.android.graphic.alignable._
import io.taig.android.graphic.positionable.Position

import scala.language.{implicitConversions, reflectiveCalls}

package object graphic
{
	implicit class RichDimension( dimensioned: { def getWidth(): Int; def getHeight(): Int } )
	{
		def getResolution = Resolution( dimensioned.getWidth(), dimensioned.getHeight() )
	}

	implicit class RichIntrinsicDimension( dimensioned: { def getIntrinsicWidth(): Int; def getIntrinsicHeight(): Int } )
	{
		def getResolution = Resolution( dimensioned.getIntrinsicWidth(), dimensioned.getIntrinsicHeight() )
	}

	implicit class RichInt( a: Int )
	{
		def x( b: Int ) = Resolution( a, b )
	}

	implicit def `Color -> Int`( color: graphic.Color ): Int = color.color

	implicit def `Float -> Alignment.Value.Relative`( value: Float ): alignable.Value.Relative = Relative( value )

	implicit def `Int -> Color`( color: Int ): graphic.Color = Color( color )

	implicit def `Int -> Alignment.Value.Absolute`( value: Int ): alignable.Value.Absolute = Absolute( value )

	implicit def `Pair -> Tuple2`[T]( pair: Pair[T] ): ( T, T ) = pair.toTuple

	implicit def `String -> Color`( hex: String ): graphic.Color = Color( hex )

	implicit def `Tuple2 -> Alignment`( t: ( alignable.Value, alignable.Value ) ): Alignment = Alignment( t._1, t._2, t._1, t._2 )

	implicit def `Tuple4 -> Alignment`( t: ( alignable.Value, alignable.Value, alignable.Value, alignable.Value ) ): Alignment = Alignment( t._1, t._2, t._3, t._4 )

	implicit def `Tuple2 -> Position`( coordinates: ( Int, Int ) ): Position = Position( coordinates._1, coordinates._2 )

	implicit def `Tuple2 -> Resolution`( resolution: ( Int, Int ) ): Resolution = Resolution( resolution._1, resolution._2 )

	implicit def `Value -> Alignment`( value: alignable.Value ): Alignment = Alignment( value, value, value, value )
}