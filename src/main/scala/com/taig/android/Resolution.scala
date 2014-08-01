package com.taig.android

case class Resolution( width: Int, height: Int )
{
	def *( ratio: ( Float, Float ) ) = Resolution( ( width * ratio._1 ).toInt, ( height * ratio._2 ).toInt )

	override def toString = s"$width x $height"
}

object Resolution
{
	def apply( resolution: Array[Int] ): Resolution =
	{
		require( resolution.length == 2 )
		apply( resolution( 0 ), resolution( 1 ) )
	}
}