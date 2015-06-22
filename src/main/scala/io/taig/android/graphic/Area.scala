package io.taig.android.graphic

import scala.math.Numeric.Implicits._

/**
 * A dimension at a certain point
 */
case class Area[T: Numeric]( position: Point[T], dimension: Dimension[T] )
{
	/**
	 * Calculate the distance of this Area to the Dimension's edges
	 */
	def distanceTo( dimension: Dimension[T] ) =
	{
		Distance(
			position.x,
			position.y,
			dimension.width - position.x + this.dimension.width,
			dimension.height - position.y + this.dimension.height
		)
	}

	override def toString = s"$dimension @ $position"
}