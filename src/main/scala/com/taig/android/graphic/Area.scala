package com.taig.android.graphic

import android.os.{Parcel, Parcelable}
import com.taig.android.graphic.positionable.Position
import com.taig.android.parcel._

/**
 * An area within a coordinate based element
 * 
 * @param position The Area's starting point
 * @param resolution The Area's size measured from the given position
 */
case class Area( position: Position, resolution: Resolution ) extends Parcelable
{
	override def writeToParcel( destination: Parcel, flags: Int ) =
	{
		destination.writeParcelable( position, flags )
		destination.writeParcelable( resolution, flags )
	}

	override def describeContents = 0
}

object Area
{
	val CREATOR: Parcelable.Creator[Area] = ( source: Parcel ) => Area( source.readParcel, source.readParcel )
}