package com.taig.android.graphic

import scala.math._

import android.os.{Parcel, Parcelable}
import com.taig.android.serialization._

case class Resolution( width: Int, height: Int ) extends Pair.Numeric with Parcelable
{
	override type S = Resolution

	override def _1 = width

	override def _2 = height

	override def map( f: ( Int, Int ) => ( Int, Int ) ) = f( width, height )

	val aspectRatio = width / height.toFloat

	/**
	 * Down- or upscale this Resolution until one of it's dimensions matches the target
	 * 
	 * @param target The target resolution
	 */
	def scaleTo( target: Resolution ) = this *
	{
		if( this > target )
		{
			max( target.width / width.toFloat, target.height / height.toFloat )
		}
		else if( this < target )
		{
			min( target.width / width.toFloat, target.height / height.toFloat )
		}
		else if( width > target.width )
		{
			target.width / width.toFloat
		}
		else if( height > target.height )
		{
			target.height / height.toFloat
		}
		else
		{
			1
		}
	}

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

object Resolution
{
	val CREATOR: Parcelable.Creator[Resolution] = ( source: Parcel ) => Resolution( source.readInt, source.readInt )
}