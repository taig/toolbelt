package io.taig.android.graphic

import android.view.View._

case class Distance[T: Numeric](left: T, top: T, right: T, bottom: T) {
  def start(direction: Int) = direction match {
    case LAYOUT_DIRECTION_LTR ⇒ left
    case LAYOUT_DIRECTION_RTL ⇒ right
  }

  def end(direction: Int) = direction match {
    case LAYOUT_DIRECTION_LTR ⇒ right
    case LAYOUT_DIRECTION_RTL ⇒ left
  }

  override def toString =
    s"left: $left, top: $top, right: $right, bottom: $bottom"
}
