package io.taig.android.graphic

import io.taig.android.Parcelable

@Parcelable
trait	Pair[T]
extends	Product2[T, T]
{
	type S <: Pair[T]

	def swap = map( ( a: T, b: T ) => ( b, a ) )

	def map( f: ( T, T ) => ( T, T ) ): S

	def toSeq = Seq( _1, _2 )

	def toTuple = ( _1, _2 )
}

object Pair
{
	@Parcelable
	trait	Numeric
	extends	Pair[Int]
	{
		override type S <: Numeric

		def +( a: Int ): S = this + ( a, a )

		def +( t: ( Int, Int ) ): S = this + ( t._1, t._2 )

		def +( a: Int, b: Int ): S = map( ( x: Int, y: Int ) => ( x + a, y + b ) )

		def -( a: Int ): S = this - ( a, a )

		def -( t: ( Int, Int ) ): S = this - ( t._1, t._2 )

		def -( a: Int, b: Int ): S = map( ( x: Int, y: Int ) => ( x - a, y - b ) )

		def *( a: Float ): S = this * ( a, a )

		def *( t: ( Float, Float ) ): S = this * ( t._1, t._2 )

		def *( a: Float, b: Float ): S = map( ( x: Int, y: Int ) => ( ( x * a ).toInt, ( y * b ).toInt ) )

		def /( a: Float ): S = this / ( a, a )

		def /( t: ( Float, Float ) ): S = this / ( t._1, t._2 )

		def /( a: Float, b: Float ): S = map( ( x: Int, y: Int ) => ( ( x / a ).toInt, ( y / b ).toInt ) )
	}
}