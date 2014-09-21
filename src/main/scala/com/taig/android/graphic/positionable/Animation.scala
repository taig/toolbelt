package com.taig.android.graphic.positionable

import com.taig.android.graphic.Positionable

case class Animation( start: Position, end: Position ) extends Positionable
{
	override var x = start.x

	override var y = start.y
}