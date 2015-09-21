package io.taig.android.widget.operation

import io.taig.android.graphic.Direction._
import io.taig.android.graphic.Distance

abstract class View( view: android.view.View ) {
    def getPadding = Distance( view.getPaddingLeft, view.getPaddingTop, view.getPaddingRight, view.getPaddingBottom )

    /**
     * Apply same padding to all Directions
     */
    def setPadding( padding: Int ) = view.setPadding( padding, padding, padding, padding )

    /**
     * Apply vertical and horizontal padding
     */
    def setPadding( vertical: Int, horizontal: Int ) = view.setPadding( horizontal, vertical, horizontal, vertical )

    def setPadding( padding: Distance[Int] ) = view.setPadding( padding.left, padding.top, padding.right, padding.bottom )

    def setPadding( direction: Direction, padding: Int ) = direction match {
        case Left   ⇒ view.setPadding( padding, view.getPaddingTop, view.getPaddingRight, view.getPaddingBottom )
        case Top    ⇒ view.setPadding( view.getPaddingLeft, padding, view.getPaddingRight, view.getPaddingBottom )
        case Right  ⇒ view.setPadding( view.getPaddingLeft, view.getPaddingTop, padding, view.getPaddingBottom )
        case Bottom ⇒ view.setPadding( view.getPaddingLeft, view.getPaddingTop, view.getPaddingRight, padding )
    }

    def next(): Option[android.view.View] = view match {
        case viewGroup: android.view.ViewGroup if viewGroup.getChildCount > 0 ⇒ Some( viewGroup.getChildAt( 0 ) )
        case view ⇒ view.getParent match {
            case viewGroup: android.view.ViewGroup ⇒
                Option( viewGroup.getChildAt( viewGroup.indexOfChild( view ) + 1 ) )
            case _ ⇒ None
        }
    }
}