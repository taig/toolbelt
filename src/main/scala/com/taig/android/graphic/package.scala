package com.taig.android

import com.taig.android.graphic.alignable.Alignment
import Alignment.Value
import Alignment.Value._
import com.taig.android.graphic.positionable.Position

package object graphic
{
	implicit class RichInt( a: Int )
	{
		def x( b: Int ) = Resolution( a, b )
	}

	implicit def `Color -> Int`( color: Color ) = color.color

	implicit def `Float -> Alignment.Value.Relative`( value: Float ) = Relative( value )

	implicit def `Int -> Color`( color: Int ) = Color( color )

	implicit def `Int -> Alignment.Value.Absolute`( value: Int ) = Absolute( value )

	implicit def `String -> Color`( hex: String ) = Color( hex )

	implicit def `Tuple2 -> Alignment`( t: ( Value, Value ) ) = Alignment( t._1, t._2, t._1, t._2 )

	implicit def `Tuple4 -> Alignment`( t: ( Value, Value, Value, Value ) ) = Alignment( t._1, t._2, t._3, t._4 )

	implicit def `Tuple2 -> Position`( coordinates: ( Int, Int ) ) = Position( coordinates._1, coordinates._2 )

	implicit def `Value -> Alignment`( value: Value ) = Alignment( value, value, value, value )

	implicit def `Tuple4 -> Animation.Position`( t: ( Value, Value, Value, Value ) ) = Alignment( t._1, t._2, t._3, t._4 )
}