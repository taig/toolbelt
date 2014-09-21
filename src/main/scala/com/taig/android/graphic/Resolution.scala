package com.taig.android.graphic

import android.os.{Parcel, Parcelable}
import android.util.Log
import com.taig.android.parcel._

case class Resolution( width: Int, height: Int ) extends Pair.Numeric[Resolution]( width, height ) with Parcelable
{
	override val companion = Resolution

	lazy val aspectRatio = width / height.toFloat

	/**
	 * Down- or upscale this Resolution until one of it's dimensions matches the target
	 * 
	 * @param target The target resolution
	 */
	def scaleTo( target: Resolution ) =
	{
		if( this > target )
		{
			this * Math.max( target.width / width.toFloat, target.height / height.toFloat )
		}
		else if( this < target )
		{
			this * Math.min( target.width / width.toFloat, target.height / height.toFloat )
		}
		else if( width > target.width )
		{
			this * ( target.width / width.toFloat )
		}
		else if( height > target.height )
		{
			this * ( target.height / height.toFloat )
		}
		else
		{
			this
		}
	}

	/**
	 * Reduce the resolution down (but not up) to the target resolution
	 * 
	 * @param target Resolution to adjust to
	 * @return Adjusted resolution
	 */
	def adjustTo( target: Resolution ) = Resolution( Math.min( width, target.width ), Math.min( height, target.height ) )

	def ratio( resolution: Resolution ) = ( width / resolution.width.toFloat, height / resolution.height.toFloat )

	def >( resolution: Resolution ) = width > resolution.width && height > resolution.height

	def >=( resolution: Resolution ) = width >= resolution.width && height >= resolution.height

	def <( resolution: Resolution ) = width < resolution.width && height < resolution.height

	def <=( resolution: Resolution ) = width <= resolution.width && height <= resolution.height

	override def toString = s"$width x $height"

	override def writeToParcel( destination: Parcel, flags: Int )
	{
		destination.writeInt( width )
		destination.writeInt( height )
	}

	override def describeContents = 0
}

object Resolution extends Pair.Companion[Resolution, Int]
{
	override def apply( tuple: ( Int, Int ) ) = Resolution( tuple._1, tuple._2 )

	val CREATOR: Parcelable.Creator[Resolution] = ( source: Parcel ) => Resolution( source.readInt, source.readInt )
}