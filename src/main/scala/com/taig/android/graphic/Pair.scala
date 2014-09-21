package com.taig.android.graphic

abstract class Pair[R <: Pair[R, T], T]( val _1: T, val _2: T ) extends Product2[T, T]
{
	def toSeq = Seq( _1, _2 )

	def toTuple = ( _1, _2 )

	val companion: Pair.Companion[R, T]
}

object Pair
{
	trait Companion[R, T]
	{
		def apply( tuple: ( T, T ) ): R

		def apply( array: Array[T] ): R =
		{
			require( array.length == 2 )

			apply( array( 0 ), array( 1 ) )
		}
	}

	abstract class Numeric[R <: Numeric[R]]( _1: Int, _2: Int ) extends Pair[R, Int]( _1, _2 )
	{
		def +( scalar: Int ): R = this.+( scalar, scalar )

		def +( vector: ( Int, Int ) ): R = companion( _1 + vector._1, _2 + vector._2 )

		def +( pair: Pair.Numeric[_] ): R = companion( _1 + pair._1, _2 + pair._2 )

		def -( scalar: Int ): R = this.-( scalar, scalar )

		def -( vector: ( Int, Int ) ): R = companion( _1 - vector._1, _2 - vector._2 )

		def -( pair: Pair.Numeric[_] ): R = companion( _1 - pair._1, _2 - pair._2 )

		def *( scalar: Float ): R = this.*( scalar, scalar )

		def *( vector: ( Float, Float ) ): R = companion( ( _1 * vector._1 ).toInt, ( _2 * vector._2 ).toInt )

		def /( scalar: Float ): R = this./( scalar, scalar )

		def /( vector: ( Float, Float ) ): R = companion( ( _1 / vector._1 ).toInt, ( _2 / vector._2 ).toInt )
	}
}