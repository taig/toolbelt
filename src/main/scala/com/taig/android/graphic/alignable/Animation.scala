package com.taig.android.graphic.alignable

import com.taig.android.graphic._
import com.taig.android.graphic.positionable.Position

import scala.math._

case class Animation( start: Alignment, end: Alignment ) extends Alignable
{
	override def resolve( resolution: Resolution, target: Resolution ) =
	{
		positionable.Animation( start.resolve( resolution, target ), end.resolve( resolution, target ) )
	}

	override def clip( resolution: Resolution, target: Resolution ) =
	{
		val position = new
		{
			val animation = resolve( resolution, target ).apply( ( a: Int, b: Int ) => ( -min( a, 0 ), -min( b, 0 ) ) )
			val start = Position( min( animation.start.x, animation.end.x ), min( animation.start.y, animation.end.y ) )
			val end = Position( max( animation.start.x, animation.end.x ), max( animation.start.y, animation.end.y ) )
		}

		Area(
			position.start,
			Resolution(
				min( target.width + ( position.end.x - position.start.x ), resolution.width ),
				min( target.height + ( position.end.y - position.start.y ), resolution.height )
			)
		)
	}
}