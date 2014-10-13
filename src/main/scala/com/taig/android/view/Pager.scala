package com.taig.android.view

import android.content.Context
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.view.MotionEvent

/**
 * A ViewPager implementation that allows to enable and disable swipe navigation
 */
class Pager( context: Context, attributes: AttributeSet ) extends ViewPager( context, attributes )
{
	private var swipe = true

	def this( context: Context ) = this( context, null )

	def isSwipeEnabled = swipe

	def setSwipeEnabled( enabled: Boolean ) = swipe = enabled

	override def onTouchEvent( event: MotionEvent ) = if( swipe )
	{
		super.onTouchEvent( event )
	}
	else
	{
		false
	}

	override def onInterceptTouchEvent( event: MotionEvent ) = if( swipe )
	{
		super.onInterceptTouchEvent( event )
	}
	else
	{
		false
	}
}