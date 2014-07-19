package com.taig.android

case class Resolution( width: Int, height: Int )
{
	def *( ratio: ( Float, Float ) ) = Resolution( ( width * ratio._1 ).toInt, ( height * ratio._2 ).toInt )

	override def toString = s"$width x $height"
}