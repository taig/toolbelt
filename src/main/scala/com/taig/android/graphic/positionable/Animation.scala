package com.taig.android.graphic.positionable

import com.taig.android.graphic._
import io.taig.android.parcelable.annotation.Parcelable

@Parcelable
case class	Animation( start: Position, end: Position, var x: Int, var y: Int )
extends		Positionable
{
	override type S = Animation

	override def map( f: ( Int, Int ) => ( Int, Int ) ) = Animation( start.map( f ), end.map( f ), f( x, y ) )

	override def apply( f: ( Int, Int ) => ( Int, Int ) ) =
	{
		start.apply( f )
		end.apply( f )
		super.apply( f )
	}

	def progress( value: Float )
	{
		require( value >= 0 && value <= 1, "Animation progress parameter must be 0 <= x <= 1" )

		x = start.x + ( ( end.x - start.x ) * value ).toInt
		y = start.y + ( ( end.y - start.y ) * value ).toInt
	}

	override def toString = s"$start -> $end: ${super.toString}"
}

object Animation
{
	def apply( start: Position, end: Position ): Animation = Animation( start, end, start.x, start.y )

	def apply( start: Position, end: Position, position: Position ): Animation = Animation( start, end, position.x, position.y )
}