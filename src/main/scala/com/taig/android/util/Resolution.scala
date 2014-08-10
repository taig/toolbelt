package com.taig.android.util

import android.os.Parcel
import android.os.Parcelable.Creator
import com.taig.android.parcel.Parcelable

case class Resolution( width: Int, height: Int ) extends Parcelable
{
	def *( ratio: ( Float, Float ) ) = Resolution( ( width * ratio._1 ).toInt, ( height * ratio._2 ).toInt )

	override def toString = s"$width x $height"

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

	val CREATOR = new Creator[Resolution]()
	{
		override def createFromParcel( source: Parcel ) = Resolution( source.readInt(), source.readInt() )

		override def newArray( size: Int ) = new Array[Resolution]( size )
	}
}