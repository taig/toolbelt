package com.taig.android.graphic.alignable

import android.os.Parcel
import com.taig.android.parcelable.Creator
import com.taig.android.parcelable.annotation.Parcelable

@Parcelable
trait Value

object Value
{
	trait Resolvable
	{
		def resolve( dimension: Int ): Int
	}

	@Parcelable
	case class	Absolute( value: Int )
	extends		Value
	with		Resolvable
	{
		override def resolve( dimension: Int ) = value
	}

	class	Auto
	extends	Value
	with	android.os.Parcelable
	{
		override def describeContents() = 0

		override def writeToParcel( destination: Parcel, flags: Int ) = {}
	}

	object	Auto
	extends	Auto
	with	Creator[Auto]
	{
		override def toString = "Auto"

		override lazy val CREATOR = new android.os.Parcelable.Creator[Auto]
		{
			override def createFromParcel( source: Parcel ) = Auto

			override def newArray( size: Int ) = new Array[Auto]( size )
		}
	}

	@Parcelable
	case class	Relative( value: Float )
	extends		Value
	with		Resolvable
	{
		require( value >= 0 && value <= 1, "Relative value must be between 0 and 1" )

		override def resolve( dimension: Int ) = ( dimension * value ).toInt
	}
}