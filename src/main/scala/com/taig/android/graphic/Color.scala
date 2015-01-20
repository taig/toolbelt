package com.taig.android.graphic

import android.graphics.Color._

case class	Color( color: Int )
extends		AnyVal
{
	def darken( amount: Float ) =
	{
		val hsv = new Array[Float]( 3 )
		colorToHSV( color, hsv )
		hsv( 2 ) *= 1 - amount
		Color( HSVToColor( hsv ) )
	}
}

object Color
{
	val Black = Color( BLACK )

	val Blue = Color( BLUE )

	val Cyan = Color( CYAN )

	val DarkGray = Color( DKGRAY )

	val Gray = Color( GRAY )

	val Green = Color( GREEN )

	val LightGray = Color( LTGRAY )

	val Magenta = Color( MAGENTA )

	val Transparent = Color( TRANSPARENT )

	val Red = Color( RED )

	val White = Color( WHITE )

	val Yellow = Color( YELLOW )

	def apply( color: String ): Color = Color( parseColor( color ) )
}