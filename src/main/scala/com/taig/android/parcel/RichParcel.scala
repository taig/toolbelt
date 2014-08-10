package com.taig.android.parcel

import android.os.Parcel
import android.{ os => android }

import scala.reflect.ClassTag

class RichParcel( parcel: Parcel )
{
	def writeBoolean( boolean: Boolean ) = parcel.writeValue( boolean )

	def readBoolean() = parcel.readValue( classOf[Boolean].getClassLoader ).asInstanceOf[Boolean]

	def readParcelable[T <: android.Parcelable: ClassTag] =
	{
		parcel.readParcelable[T]( implicitly[ClassTag[T]].runtimeClass.getClassLoader )
	}
}