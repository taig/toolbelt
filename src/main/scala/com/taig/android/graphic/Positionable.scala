package com.taig.android.graphic

import com.taig.android.parcelable.annotation.Parcelable

import scala.math._

@Parcelable
trait	Positionable
extends	Pair.Numeric
{
	override type S <: Positionable

	var x: Int

	var y: Int

	override def _1 = x

	override def _2 = y

	def apply( f: ( Int, Int ) => ( Int, Int ) ): this.type =
	{
		val t = f( x, y )
		x = t._1
		y = t._2
		this
	}

	/**
	 * Calculate the distance between this and the given position (Pythagoras)
	 * 
	 * @param that How far is that position away?
	 * @return The distance between the positions
	 */
	def distanceTo( that: Positionable ) = sqrt( pow( x - that.x, 2 ) + pow( y - that.y, 2 ) )

	def +=( a: Int ): this.type = this += ( a, a )

	def +=( t: ( Int, Int ) ): this.type = this += ( t._1, t._2 )
	
	def +=( a: Int, b: Int ): this.type = apply( ( x: Int, y: Int ) => ( x + a, y + b ) )

	def -=( a: Int ): this.type = this -= ( a, a )

	def -=( t: ( Int, Int ) ): this.type = this -= ( t._1, t._2 )

	def -=( a: Int, b: Int ): this.type = apply( ( x: Int, y: Int ) => ( x - a, y - b ) )

	def *=( a: Float ): this.type = this *= ( a, a )

	def *=( t: ( Float, Float ) ): this.type = this *= ( t._1, t._2 )

	def *=( a: Float, b: Float ): this.type = apply( ( x: Int, y: Int ) => ( ( x * a ).toInt, ( y * b ).toInt ) )

	def /=( a: Float ): this.type = this /= ( a, a )

	def /=( t: ( Float, Float ) ): this.type = this /= ( t._1, t._2 )

	def /=( a: Float, b: Float ): this.type = apply( ( x: Int, y: Int ) => ( ( x / a ).toInt, ( y / b ).toInt ) )

	override def toString = s"($x, $y)"
}