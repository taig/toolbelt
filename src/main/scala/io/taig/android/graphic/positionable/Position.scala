package io.taig.android.graphic.positionable

import io.taig.android.graphic.Positionable
import io.taig.android.Parcelable

@Parcelable
case class	Position( var x: Int, var y: Int )
extends		Positionable
{
	override type S = Position

	override def map( f: ( Int, Int ) => ( Int, Int ) ) = f( x, y )
}