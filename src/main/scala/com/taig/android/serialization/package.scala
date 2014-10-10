package com.taig.android

import android.os.Parcel

import scala.reflect.ClassTag

package object serialization
{
	implicit class RichParcel( parcel: Parcel )
	{
		def writeBoolean( boolean: Boolean ) = parcel.writeValue( boolean )

		def readBoolean = parcel
			.readValue( classOf[Boolean].getClassLoader )
			.asInstanceOf[Boolean]

		def writeOption[T]( option: Option[T] ) = parcel.writeValue( option.orNull )

		def readOption[T: ClassTag] = Option
		{
			parcel
				.readValue( implicitly[ClassTag[T]].runtimeClass.getClassLoader )
				.asInstanceOf[T]
		}

		def readParcel[T <: android.os.Parcelable: ClassTag] = parcel
			.readParcelable[T]( implicitly[ClassTag[T]].runtimeClass.getClassLoader )

		def readParcelArray[T <: android.os.Parcelable: ClassTag] = parcel
			.readParcelableArray( implicitly[ClassTag[T]].runtimeClass.getClassLoader )
			.map( _.asInstanceOf[T] )

		def readSerialized[T] = parcel
			.readSerializable
			.asInstanceOf[T]
	}

	implicit def `Function1 -> T -> T`[T <: Object: ClassTag]( f: Parcel => T ) = new android.os.Parcelable.Creator[T]
	{
		override def createFromParcel( source: Parcel ): T = f( source )

		override def newArray( size: Int ) = new Array[T]( size )
	}
}