package com.taig.android.graphic.alignable

import com.taig.android.graphic._

case class Animation( start: Alignment, end: Alignment ) extends Alignable
{
	override def resolve( resolution: Resolution, target: Resolution ): positionable.Animation =
	{
		positionable.Animation( start.resolve( resolution, target ), end.resolve( resolution, target ) )
	}
}