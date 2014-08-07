package com.taig.android.util

import android.os.{Parcel, Parcelable}

case class Resolution( width: Int, height: Int ) extends Parcelable
{
	def *( ratio: ( Float, Float ) ) = Resolution( ( width * ratio._1 ).toInt, ( height * ratio._2 ).toInt )

	override def toString = s"$width x $height"

	override def describeContents() = 0

	override def writeToParcel( destination: Parcel, flags: Int )
	{
		destination.writeInt( width )
		destination.writeInt( height )
	}
}

object Resolution
{
	def apply( resolution: Array[Int] ): Resolution =
	{
		require( resolution.length == 2 )
		apply( resolution( 0 ), resolution( 1 ) )
	}

	val CREATOR = new Parcelable.Creator[Resolution]()
	{
		override def createFromParcel( source: Parcel ) = Resolution( source.readInt(), source.readInt() )

		override def newArray( size: Int ) = new Array[Resolution]( size )
	}
}