package com.taig.android.graphic.alignable

import java.lang.Math._

import com.taig.android.graphic.alignable.Alignment.Value.Auto
import com.taig.android.graphic.alignable.Alignment.{Resolvable, Value}
import com.taig.android.graphic.positionable.Position
import com.taig.android.graphic.{Alignable, Resolution}

case class Alignment( top: Value = Auto, right: Value = Auto, bottom: Value = Auto, left: Value = Auto ) extends Alignable
{
	override def resolve( resolution: Resolution, target: Resolution ) =
	{
		def resolve( a: Value, b: Value, resolution: Int, target: Int ) = ( a, b ) match
		{
			case ( Auto, Auto ) => ( target - resolution ) / 2
			case ( a: Resolvable, Auto ) => a.resolve( resolution )
			case ( Auto, b: Resolvable ) => target - resolution - b.resolve( resolution )
			case ( a: Resolvable, b: Resolvable ) => min( a.resolve( resolution ), b.resolve( resolution ) )
		}

		Position(
			resolve( left, right, resolution.width, target.width ),
			resolve( top, bottom, resolution.height, target.height )
		)
	}
}

object Alignment
{
	trait Value

	trait Resolvable
	{
		def resolve( dimension: Int ): Int
	}

	object Value
	{
		case class Absolute( value: Int ) extends Value with Resolvable
		{
			override def resolve( dimension: Int ) = value
		}

		object Auto extends Value
		{
			override def toString = "Auto"
		}

		case class Relative( value: Float ) extends Value with Resolvable
		{
			require( value >= 0 && value <= 1, "Relative value must be between 0 and 1" )

			override def resolve( dimension: Int ) = ( dimension * value ).toInt
		}
	}
}