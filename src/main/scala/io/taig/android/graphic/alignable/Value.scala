package io.taig.android.graphic.alignable

import io.taig.android.Parcelable

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

	@Parcelable
	object	Auto
	extends	Value
	{
		override def toString = "Auto"
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