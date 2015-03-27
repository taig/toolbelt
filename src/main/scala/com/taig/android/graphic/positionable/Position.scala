package com.taig.android.graphic.positionable

import com.taig.android.graphic.Positionable
import io.taig.android.parcelable.annotation.Parcelable

@Parcelable
case class	Position( var x: Int, var y: Int )
extends		Positionable
{
	override type S = Position

	override def map( f: ( Int, Int ) => ( Int, Int ) ) = f( x, y )
}