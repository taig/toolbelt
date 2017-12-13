package io.taig.android.widget.operation

import android.view.{View, ViewGroup}
import io.taig.android.widget.syntax.view._
import io.taig.android.widget.syntax.viewGroup._

final class viewGroup(viewGroup: ViewGroup) extends Iterable[View] {

  /**
    * Recursively discovers all children of this view and flattens them into a one dimensional collection in no
    * particular order
    */
  def childrenRecursive = {
    def discover(view: View): Seq[View] = view match {
      case viewGroup: ViewGroup ⇒
        viewGroup.children.flatMap(discover) :+ view
      case _ ⇒ Seq(view)
    }

    discover(viewGroup)
  }

  /**
    * Get a list of all direct child views
    */
  def children: Seq[View] = {
    (0 until viewGroup.getChildCount)
      .map(viewGroup.getChildAt)
  }

  override def iterator = new Iterator[View] {
    var current: View = viewGroup

    override def hasNext = current.next.isDefined

    override def next() = {
      current = current.next.orNull
      current
    }
  }

  /**
    * Add view as first child
    */
  def prependView(view: View) = viewGroup.addView(view, 0)

  /**
    * Add view as first child
    */
  def prependView(view: View, parameters: ViewGroup.LayoutParams) = {
    viewGroup.addView(view, 0, parameters)
  }

  /**
    * Add view as last child
    */
  def appendView(view: View) = viewGroup.addView(view, viewGroup.getChildCount)

  /**
    * Add view as last child
    */
  def appendView(view: View, parameters: ViewGroup.LayoutParams) = {
    viewGroup.addView(view, viewGroup.getChildCount, parameters)
  }
}
