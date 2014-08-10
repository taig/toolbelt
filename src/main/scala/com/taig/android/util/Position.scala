package com.taig.android.util

import android.os.Parcel
import android.os.Parcelable.Creator
import com.taig.android.parcel.Parcelable

case class Position( var x: Int, var y: Int ) extends Parcelable
{
	override def toString = s"($x, $y)"

	override def writeToParcel( destination: Parcel, flags: Int )
	{
		destination.writeInt( x )
		destination.writeInt( y )
	}
}

object Position
{
	def apply( position: Array[Int] ): Position =
	{
		require( position.length == 2 )

		apply( position( 0 ), position( 1 ) )
	}

	val CREATOR = new Creator[Position]()
	{
		override def createFromParcel( source: Parcel ) = Position( source.readInt(), source.readInt() )

		override def newArray( size: Int ) = new Array[Position]( size )
	}
}