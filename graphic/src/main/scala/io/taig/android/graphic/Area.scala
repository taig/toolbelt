package io.taig.android.graphic

import io.taig.android.graphic.Direction._

import scala.math.Numeric.Implicits._

/**
  * A dimension at a certain point
  */
case class Area[T: Numeric](position: Point[T], dimension: Dimension[T]) {

  /**
    * Calculate the distance of this Area to the Dimension's edges
    */
  def distanceTo(dimension: Dimension[T]) = {
    Distance(
      position.x,
      position.y,
      dimension.width - position.x + this.dimension.width,
      dimension.height - position.y + this.dimension.height
    )
  }

  /**
    * Get the position of a corner (e.g. <code>corner( Bottom, Right ) -> Position( 10, 20 )</code>)
    */
  def corner(a: Direction, b: Direction): Point[T] = (a, b) match {
    case (Top, Left) | (Left, Top) ⇒ position
    case (Top, Right) | (Right, Top) ⇒
      position.copy(x = position.x + dimension.width)
    case (Bottom, Right) | (Right, Bottom) ⇒ position + dimension
    case (Bottom, Left) | (Left, Bottom) ⇒
      position.copy(y = position.y + dimension.height)
    case _ ⇒ sys.error(s"Corner ( $a, $b ) does not make sense")
  }

  /**
    * Get the area of a corner (e.g. <code>edge( Bottom ) -> Area( Position( 10, 20 ), Dimension( 50, 0 ) )</code>)
    */
  def edge(direction: Direction): Area[T] = direction match {
    case Left ⇒
      Area(position, dimension.copy(width = implicitly[Numeric[T]].zero))
    case Top ⇒
      Area(position, dimension.copy(height = implicitly[Numeric[T]].zero))
    case Right ⇒
      Area(position.copy(x = position.x + dimension.width),
           dimension.copy(width = implicitly[Numeric[T]].zero))
    case Bottom ⇒
      Area(position.copy(y = position.y + dimension.height),
           dimension.copy(height = implicitly[Numeric[T]].zero))
  }

  override def toString = s"$dimension @ $position"
}
