package com.taig.android


import android.os.{IBinder, Parcelable, Bundle, Parcel}
import android.util.SparseArray

import scala.reflect.ClassTag

package object serialization
{
	private type ArrayList[T] = java.util.ArrayList[T]

	private object Tag
	{
		val Array = new
		{
			val Boolean = implicitly[ClassTag[Array[Boolean]]]

			val Byte = implicitly[ClassTag[Array[Byte]]]

			val Char = implicitly[ClassTag[Array[Char]]]

			val CharSequence = implicitly[ClassTag[Array[CharSequence]]]
			
			val Double = implicitly[ClassTag[Array[Double]]]
			
			val Float = implicitly[ClassTag[Array[Float]]]
			
			val Int = implicitly[ClassTag[Array[Int]]]
			
			val Long = implicitly[ClassTag[Array[Long]]]
			
			val Parcelable = implicitly[ClassTag[Array[Parcelable]]]
			
			val Short = implicitly[ClassTag[Array[Short]]]
			
			val SparseParcelable = implicitly[ClassTag[Array[SparseArray[_ <: Parcelable]]]]
			
			val String = implicitly[ClassTag[Array[String]]]
		}

		val List = new
		{
			val CharSequence = implicitly[ClassTag[ArrayList[CharSequence]]]
			
			val Integer = implicitly[ClassTag[ArrayList[Integer]]]
			
			val Parcelable = implicitly[ClassTag[ArrayList[Parcelable]]]

			val String = implicitly[ClassTag[ArrayList[String]]]
		}

		val IBinder = implicitly[ClassTag[IBinder]]

		val CharSequence = implicitly[ClassTag[CharSequence]]

		val Parcelable = implicitly[ClassTag[Parcelable]]

		val Serializable = implicitly[ClassTag[Serializable]]

		val String = implicitly[ClassTag[String]]
	}

	implicit class RichBundle( bundle: Bundle )
	{
		def putValue[T: ClassTag]( key: String, value: T ) = implicitly[ClassTag[T]] match
		{
			case ClassTag.Boolean => bundle.putBoolean( key, value.asInstanceOf[Boolean] )
			case ClassTag.Byte => bundle.putByte( key, value.asInstanceOf[Byte] )
			case ClassTag.Char => bundle.putChar( key, value.asInstanceOf[Char] )
			case ClassTag.Double => bundle.putDouble( key, value.asInstanceOf[Double] )
			case ClassTag.Float => bundle.putFloat( key, value.asInstanceOf[Float] )
			case ClassTag.Int => bundle.putInt( key, value.asInstanceOf[Int] )
			case ClassTag.Long => bundle.putLong( key, value.asInstanceOf[Long] )
			case ClassTag.Short => bundle.putShort( key, value.asInstanceOf[Short] )

			case Tag.IBinder => bundle.putBinder( key, value.asInstanceOf[IBinder] )
			case Tag.CharSequence => bundle.putCharSequence( key, value.asInstanceOf[CharSequence] )
			case Tag.Parcelable => bundle.putParcelable( key, value.asInstanceOf[Parcelable] )
			case Tag.Serializable => bundle.putSerializable( key, value.asInstanceOf[Serializable] )
			case Tag.String => bundle.putString( key, value.asInstanceOf[String] )

			case Tag.Array.Boolean => bundle.putBooleanArray( key, value.asInstanceOf[Array[Boolean]] )
			case Tag.Array.Byte => bundle.putByteArray( key, value.asInstanceOf[Array[Byte]] )
			case Tag.Array.Char => bundle.putCharArray( key, value.asInstanceOf[Array[Char]] )
			case Tag.Array.CharSequence => bundle.putCharSequenceArray( key, value.asInstanceOf[Array[CharSequence]] )
			case Tag.Array.Double => bundle.putDoubleArray( key, value.asInstanceOf[Array[Double]] )
			case Tag.Array.Float => bundle.putFloatArray( key, value.asInstanceOf[Array[Float]] )
			case Tag.Array.Int => bundle.putIntArray( key, value.asInstanceOf[Array[Int]] )
			case Tag.Array.Long => bundle.putLongArray( key, value.asInstanceOf[Array[Long]] )
			case Tag.Array.Parcelable => bundle.putParcelableArray( key, value.asInstanceOf[Array[Parcelable]] )
			case Tag.Array.Short => bundle.putShortArray( key, value.asInstanceOf[Array[Short]] )
			case Tag.Array.SparseParcelable => bundle.putSparseParcelableArray( key, value.asInstanceOf[SparseArray[_ <: Parcelable]] )
			case Tag.Array.String => bundle.putStringArray( key, value.asInstanceOf[Array[String]] )

			case Tag.List.CharSequence => bundle.putCharSequenceArrayList( key, value.asInstanceOf[ArrayList[CharSequence]] )
			case Tag.List.Integer => bundle.putIntegerArrayList( key, value.asInstanceOf[ArrayList[Integer]] )
			case Tag.List.Parcelable => bundle.putParcelableArrayList( key, value.asInstanceOf[ArrayList[Parcelable]] )
			case Tag.List.String => bundle.putStringArrayList( key, value.asInstanceOf[ArrayList[String]] )

			case x => throw new RuntimeException( s"Could not handle '${x.runtimeClass.getName}'" )
		}

		def getValue[T: ClassTag]( key: String, default: T ): T = implicitly[ClassTag[T]] match
		{
			case ClassTag.Boolean => bundle.getBoolean( key, default.asInstanceOf[Boolean] ).asInstanceOf[T]
			case ClassTag.Byte => bundle.getByte( key, default.asInstanceOf[Byte] ).asInstanceOf[T]
			case ClassTag.Char => bundle.getChar( key, default.asInstanceOf[Char] ).asInstanceOf[T]
			case ClassTag.Double => bundle.getDouble( key, default.asInstanceOf[Double] ).asInstanceOf[T]
			case ClassTag.Float => bundle.getFloat( key, default.asInstanceOf[Float] ).asInstanceOf[T]
			case ClassTag.Int => bundle.getInt( key, default.asInstanceOf[Int] ).asInstanceOf[T]
			case ClassTag.Long => bundle.getLong( key, default.asInstanceOf[Long] ).asInstanceOf[T]
			case ClassTag.Short => bundle.getShort( key, default.asInstanceOf[Short] ).asInstanceOf[T]

			case Tag.CharSequence => bundle.getCharSequence( key, default.asInstanceOf[CharSequence] ).asInstanceOf[T]
			case Tag.String => bundle.getString( key, default.asInstanceOf[String] ).asInstanceOf[T]

			case x => throw new RuntimeException( s"Could not handle '${x.runtimeClass.getName}'" )
		}

		def getValue[T: ClassTag]( key: String ): Option[T] = Option
		{
			implicitly[ClassTag[T]] match
			{
				case ClassTag.Boolean => bundle.getBoolean( key ).asInstanceOf[T]
				case ClassTag.Byte => bundle.getByte( key ).asInstanceOf[T]
				case ClassTag.Char => bundle.getChar( key ).asInstanceOf[T]
				case ClassTag.Double => bundle.getDouble( key ).asInstanceOf[T]
				case ClassTag.Float => bundle.getFloat( key ).asInstanceOf[T]
				case ClassTag.Int => bundle.getInt( key ).asInstanceOf[T]
				case ClassTag.Long => bundle.getLong( key ).asInstanceOf[T]
				case ClassTag.Short => bundle.getShort( key ).asInstanceOf[T]

				case Tag.IBinder => bundle.getBinder( key ).asInstanceOf[T]
				case Tag.CharSequence => bundle.getCharSequence( key ).asInstanceOf[T]
				case Tag.Parcelable => bundle.getParcelable( key ).asInstanceOf[T]
				case Tag.Serializable => bundle.getSerializable( key ).asInstanceOf[T]
				case Tag.String => bundle.getString( key ).asInstanceOf[T]

				case Tag.Array.Boolean => bundle.getBooleanArray( key ).asInstanceOf[T]
				case Tag.Array.Byte => bundle.getByteArray( key ).asInstanceOf[T]
				case Tag.Array.Char => bundle.getCharArray( key ).asInstanceOf[T]
				case Tag.Array.CharSequence => bundle.getCharSequenceArray( key ).asInstanceOf[T]
				case Tag.Array.Double => bundle.getDoubleArray( key ).asInstanceOf[T]
				case Tag.Array.Float => bundle.getFloatArray( key ).asInstanceOf[T]
				case Tag.Array.Int => bundle.getIntArray( key ).asInstanceOf[T]
				case Tag.Array.Long => bundle.getLongArray( key ).asInstanceOf[T]
				case Tag.Array.Parcelable => bundle.getParcelableArray( key ).asInstanceOf[T]
				case Tag.Array.Short => bundle.getShortArray( key ).asInstanceOf[T]
				case Tag.Array.SparseParcelable => bundle.getSparseParcelableArray( key ).asInstanceOf[T]
				case Tag.Array.String => bundle.getStringArray( key ).asInstanceOf[T]

				case Tag.List.CharSequence => bundle.getCharSequenceArrayList( key ).asInstanceOf[T]
				case Tag.List.Integer => bundle.getIntegerArrayList( key ).asInstanceOf[T]
				case Tag.List.Parcelable => bundle.getParcelableArrayList( key ).asInstanceOf[T]
				case Tag.List.String => bundle.getStringArrayList( key ).asInstanceOf[T]
			}
		}
	}

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

	implicit def `Bundle -> Option[Bundle]`( bundle: Bundle ) = Option( bundle )

	implicit def `Function1 -> T -> T`[T <: Object: ClassTag]( f: Parcel => T ) = new android.os.Parcelable.Creator[T]
	{
		override def createFromParcel( source: Parcel ): T = f( source )

		override def newArray( size: Int ) = new Array[T]( size )
	}
}