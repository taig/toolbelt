package com.taig.android.graphic.alignable

import android.util.Log
import com.taig.android.graphic._
import com.taig.android.graphic.alignable.Value._
import com.taig.android.graphic.positionable.Position
import com.taig.android.parcelable.annotation.Parcelable

import scala.math._

@Parcelable
case class	Alignment( top: Value = Auto, right: Value = Auto, bottom: Value = Auto, left: Value = Auto )
extends		Alignable
{
	override def resolve( resolution: Resolution, target: Resolution ) =
	{
		def resolve( a: Value, b: Value, resolution: Int, target: Int ) = ( a, b ) match
		{
			case ( Auto, Auto ) => ( target - resolution ) / 2
			case ( a: Resolvable, Auto ) => a.resolve( target )
			case ( Auto, b: Resolvable ) => target - resolution - b.resolve( target )
			// TODO center between the two offsets
			case ( a: Resolvable, b: Resolvable ) => min( a.resolve( target ), b.resolve( target ) )
		}

		Position(
			resolve( left, right, resolution.width, target.width ),
			resolve( top, bottom, resolution.height, target.height )
		)
	}

	override def clip( resolution: Resolution, target: Resolution ) =
	{
		val position = resolve( resolution, target )

		Log.d( "ASDF", "Initial Position: " + position )

		Area(
			position.map( ( a: Int, b: Int ) => ( -min( a, 0 ), -min( b, 0 ) ) ),
			Resolution(
				min( target.width + min( position.x, 0 ), resolution.width ),
				min( target.height + min( position.y, 0 ), resolution.height )
			)
		)
	}
}