package com.taig.android.parcel

import android.os.Parcel

class RichParcel( parcel: Parcel )
{
	def writeBoolean( boolean: Boolean ) = parcel.writeValue( boolean )

	def readBoolean() = parcel.readValue( classOf[Boolean].getClassLoader ).asInstanceOf[Boolean]
}
