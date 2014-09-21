package com.taig.android.graphic.positionable

import android.os.{Parcel, Parcelable}
import com.taig.android.graphic.{Positionable, Pair}
import com.taig.android.parcel._

case class Position( var x: Int, var y: Int ) extends Pair.Numeric[Position]( x, y ) with Positionable with Parcelable
{
	override val companion = Position

	override def toString = s"($x, $y)"

	override def writeToParcel( destination: Parcel, flags: Int )
	{
		destination.writeInt( x )
		destination.writeInt( y )
	}

	override def describeContents = 0
}

object Position extends Pair.Companion[Position, Int]
{
	override def apply( tuple: (Int, Int) ) = Position( tuple._1, tuple._2 )

	val CREATOR: Parcelable.Creator[Position] = ( source: Parcel ) => Position( source.readInt(), source.readInt() )
}