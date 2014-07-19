package com.taig.android

case class Position( var x: Int, var y: Int )
{
	override def toString = s"($x, $y)"
}