package com.taig.android

case class Position( var x: Int, var y: Int )
{
	override def toString = s"($x, $y)"
}

object Position
{
	def apply( position: Array[Int] ): Position =
	{
		require( position.length == 2 )
		apply( position( 0 ), position( 1 ) )
	}
}