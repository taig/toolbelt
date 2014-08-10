package com.taig.android.parcel

import android.os.Parcel

trait Parcelable extends android.os.Parcelable
{
	override def describeContents() = 0

	override def writeToParcel( destination: Parcel, flags: Int )
}