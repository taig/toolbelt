package com.taig.android

import com.taig.android.graphic.alignable.Value._
import com.taig.android.graphic.alignable._
import com.taig.android.graphic.positionable.Position

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

	implicit def `Color -> Int`( color: Color ): Int = color.color

	implicit def `Float -> Alignment.Value.Relative`( value: Float ): Relative = Relative( value )

	implicit def `Int -> Color`( color: Int ): Color = Color( color )

	implicit def `Int -> Alignment.Value.Absolute`( value: Int ): Absolute = Absolute( value )

	implicit def `Pair -> Tuple2`[T]( pair: Pair[T] ): ( T, T ) = pair.toTuple

	implicit def `String -> Color`( hex: String ): Color = Color( hex )

	implicit def `Tuple2 -> Alignment`( t: ( Value, Value ) ): Alignment = Alignment( t._1, t._2, t._1, t._2 )

	implicit def `Tuple4 -> Alignment`( t: ( Value, Value, Value, Value ) ): Alignment = Alignment( t._1, t._2, t._3, t._4 )

	implicit def `Tuple2 -> Position`( coordinates: ( Int, Int ) ): Position = Position( coordinates._1, coordinates._2 )

	implicit def `Tuple2 -> Resolution`( resolution: ( Int, Int ) ): Resolution = Resolution( resolution._1, resolution._2 )

	implicit def `Value -> Alignment`( value: Value ): Alignment = Alignment( value, value, value, value )
}