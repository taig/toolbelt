package io.taig.android.widget

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent

/**
 * Advanced [[android.support.v4.view.ViewPager]] that allows to toggle swiping
 */
class ViewPager( context: Context, attributes: AttributeSet )
        extends android.support.v4.view.ViewPager( context, attributes ) {
    private var swipe = true

    def this( attributes: AttributeSet )( implicit context: Context ) = this( context, null )

    def this()( implicit context: Context ) = this( context, null )

    def setSwipeEnabled( enabled: Boolean ): Unit = {
        swipe = enabled
    }

    override def onInterceptTouchEvent( event: MotionEvent ) = swipe match {
        case true  ⇒ super.onInterceptTouchEvent( event )
        case false ⇒ false
    }

    override def onTouchEvent( event: MotionEvent ) = swipe match {
        case true  ⇒ super.onTouchEvent( event )
        case false ⇒ false
    }
}