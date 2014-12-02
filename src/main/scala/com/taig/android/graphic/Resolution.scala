package com.taig.android.graphic

import android.os.{Parcel, Parcelable}
import com.taig.android.serialization._

import scala.math._

case class	Resolution( width: Int, height: Int ) 
extends		Pair.Numeric
with		Parcelable
{
	override type S = Resolution

	override def _1 = width

	override def _2 = height

	override def map( f: ( Int, Int ) => ( Int, Int ) ) = f( width, height )

	/**
	 * Aspect ration of this Resolution
	 * 
	 * @return ( with to height, height to width )
	 */
	def getAspectRatio() = ( width / height.toFloat, height / width.toFloat )

	/**
	 * Compare with and height of this Resolution with the target
	 * 
	 * @return ( width to target width, height to target height )
	 */
	def getRatioTo( target: Resolution ) = ( target.width / width.toFloat, target.height / height.toFloat )

	/**
	 * Down- or upscale this Resolution until one of it's dimensions matches the target
	 * 
	 * @param target The target resolution
	 */
	def scaleTo( target: Resolution ) = this *
	{
		lazy val ratio = getRatioTo( target )

		if( this > target )
		{
			max( ratio._1, ratio._2 )
		}
		else if( this < target )
		{
			min( ratio._1, ratio._2 )
		}
		else if( width > target.width )
		{
			ratio._1
		}
		else if( height > target.height )
		{
			ratio._2
		}
		else
		{
			1
		}
	}

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

	def apply( dimensioned: { def getWidth(): Int; def getHeight(): Int } ): Resolution =
	{
		apply( dimensioned.getWidth(), dimensioned.getHeight() )
	}
}