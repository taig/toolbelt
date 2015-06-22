package io.taig.android.graphic

import scala.math.Numeric.Implicits._
import scala.math.Ordering.Implicits._

abstract class	Pair[T: Numeric]( val _1: T, val _2: T )
extends			Product2[T, T]
{
	type S <: Pair[T]

	protected def apply( _1: T, _2: T ): S

	def swap = apply( _2, _1 )

	def toSeq = Seq( _1, _2 )

	def toTuple = ( _1, _2 )

	def +( rhs: T ): S = apply( _1 + rhs, _2 + rhs )

	def +( rhs: Pair[T] ): S = apply( _1 + rhs._1, _2 + rhs._2 )

	def -( rhs: T ): S = apply( _1 - rhs, _2 - rhs )

	def -( rhs: Pair[T] ): S = apply( _1 - rhs._1, _2 - rhs._2 )

	def *( rhs: T ): S = apply( _1 * rhs, _2 * rhs )

	def *( rhs: Pair[T] ): S = apply( _1 * rhs._1, _2 * rhs._2 )

	def >( rhs: T ): Boolean = _1 > rhs && _2 > rhs

	def >( rhs: Pair[T] ): Boolean = _1 > rhs._1 && _2 > rhs._2

	def >=( rhs: T ): Boolean = _1 >= rhs && _2 >= rhs

	def >=( rhs: Pair[T] ): Boolean = _1 >= rhs._1 && _2 >= rhs._2

	def <( rhs: T ): Boolean = _1 < rhs && _2 < rhs

	def <( rhs: Pair[T] ): Boolean = _1 < rhs._1 && _2 < rhs._2

	def <=( rhs: T ): Boolean = _1 <= rhs && _2 <= rhs

	def <=( rhs: Pair[T] ): Boolean = _1 <= rhs._1 && _2 <= rhs._2
}