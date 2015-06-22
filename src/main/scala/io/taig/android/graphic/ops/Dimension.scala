package io.taig.android.graphic.ops

import scala.math.Integral.Implicits._
import scala.math.Ordering.Implicits._

abstract class Dimension[T: Integral]
{
	def dimension: io.taig.android.graphic.Dimension[T]

	/**
	 * Aspect ration of this Resolution
	 *
	 * @return ( with to height, height to width )
	 */
	def aspectRatio = ( dimension.width / dimension.height, dimension.height / dimension.width )

	/**
	 * Compare with and height of this Resolution with the target
	 *
	 * @return ( width to target width, height to target height )
	 */
	def ratioTo( target: io.taig.android.graphic.Dimension[T] ) =
	{
		( target.width / dimension.width, target.height / dimension.height )
	}

	/**
	 * Down- or upscale this Resolution until one of it's dimensions matches the target
	 * 
	 * @param target The target resolution
	 */
	def scaleTo( target: io.taig.android.graphic.Dimension[T] ) = dimension *
	{
		lazy val ratio = ratioTo( target )

		if( dimension > target )
		{
			implicitly[Integral[T]].max( ratio._1, ratio._2 )
		}
		else if( dimension < target )
		{
			implicitly[Integral[T]].min( ratio._1, ratio._2 )
		}
		else if( dimension.width > target.width )
		{
			ratio._1
		}
		else if( dimension.height > target.height )
		{
			ratio._2
		}
		else
		{
			implicitly[Integral[T]].fromInt( 0 )
		}
	}
}