package io.taig.android.widget.operation

import android.view.View
import io.taig.android.graphic.Direction._
import io.taig.android.graphic.Distance
import io.taig.android.widget.syntax.view._

final class view( view: View ) {
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

    def getBottomTo( parent: View ): Int = view.getParent match {
        case `parent`           ⇒ view.getBottom
        case intermediate: View ⇒ view.getBottom + intermediate.getBottomTo( parent )
        case _                  ⇒ sys.error( "Can't resolve relative bottom" )
    }

    def getLeftTo( parent: View ): Int = view.getParent match {
        case `parent`           ⇒ view.getLeft
        case intermediate: View ⇒ view.getLeft + intermediate.getLeftTo( parent )
        case _                  ⇒ sys.error( "Can't resolve relative left" )
    }

    def getRightTo( parent: View ): Int = view.getParent match {
        case `parent`           ⇒ view.getRight
        case intermediate: View ⇒ view.getRight + intermediate.getRightTo( parent )
        case _                  ⇒ sys.error( "Can't resolve relative right" )
    }

    def getTopTo( parent: View ): Int = view.getParent match {
        case `parent`           ⇒ view.getTop
        case intermediate: View ⇒ view.getTop + intermediate.getTopTo( parent )
        case _                  ⇒ sys.error( "Can't resolve relative top" )
    }

    def next: Option[View] = view match {
        case viewGroup: android.view.ViewGroup if viewGroup.getChildCount > 0 ⇒ Some( viewGroup.getChildAt( 0 ) )
        case view ⇒ view.getParent match {
            case viewGroup: android.view.ViewGroup ⇒
                Option( viewGroup.getChildAt( viewGroup.indexOfChild( view ) + 1 ) )
            case _ ⇒ None
        }
    }

    /**
     * Wrap this view with the given view, and also rewire the parent (if present)
     */
    def wrap[V <: android.view.ViewGroup]( viewGroup: V ): V = {
        view.getParent match {
            case parent: android.view.ViewGroup ⇒
                parent.removeView( view )
                parent.addView( viewGroup, view.getLayoutParams )
            case _ ⇒ //
        }

        viewGroup.addView( view )
        viewGroup
    }
}