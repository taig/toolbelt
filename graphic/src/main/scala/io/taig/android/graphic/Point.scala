package io.taig.android.graphic

import scala.language.reflectiveCalls
import scala.reflect.ClassTag
import scala.math.Numeric.Implicits._

/**
  * A simple point with x and y coordinates
  */
case class Point[T: Numeric](x: T, y: T) extends Pair[T](x, y) {
  override type S = Point[T]

  override protected def apply(x: T, y: T) = Point(x, y)

  /**
    * Calculate the distance of this Point to the Dimension's edges
    */
  def distanceTo(dimension: Dimension[T]) =
    Distance(x, y, dimension.width - x, dimension.height - y)

  override def toString = s"( $x, $y )"
}

object Point {
  val Zero = apply(0, 0)

  def apply[T: Numeric](array: Array[T]): Point[T] = {
    require(array.length == 2)

    Point(array(0), array(1))
  }

  def apply[T: Numeric: ClassTag](f: Array[T] ⇒ Unit): Point[T] = {
    val array = new Array[T](2)
    f(array)
    Point(array)
  }
}
