package com.taig.android.graphic.positionable

import android.os.{Parcel, Parcelable}
import com.taig.android.graphic.Positionable
import com.taig.android.parcel._

case class	Position( var x: Int, var y: Int )
extends		Positionable
with		Parcelable
{
	override type S = Position

	override def map( f: ( Int, Int ) => ( Int, Int ) ) = f( x, y )

	override def writeToParcel( destination: Parcel, flags: Int )
	{
		destination.writeInt( x )
		destination.writeInt( y )
	}

	override def describeContents = 0
}

object Position
{
	val CREATOR: Parcelable.Creator[Position] = ( source: Parcel ) => Position( source.readInt(), source.readInt() )
}