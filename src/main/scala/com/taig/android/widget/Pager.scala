package com.taig.android.widget

import android.content.Context
import android.content.res.TypedArray
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.view.MotionEvent
import com.taig.android.R

/**
 * A ViewPager implementation that allows to enable and disable swipe navigation
 */
class	Pager( val attributes: AttributeSet = null )( implicit context: Context )
extends	ViewPager( context, attributes )
with	Widget
{
	def this( context: Context, attributes: AttributeSet ) = this( attributes )( context )

	def this( context: Context ) = this()( context )

	private var swipe = true

	initialize( R.styleable.View_Pager, ( array: TypedArray ) =>
	{
		swipe = array.getBoolean( R.styleable.View_Pager_swipe, swipe )
	} )

	def isSwipeEnabled = swipe

	def setSwipeEnabled( enabled: Boolean ) = swipe = enabled

	override def onTouchEvent( event: MotionEvent ) = if( swipe ) super.onTouchEvent( event ) else false

	override def onInterceptTouchEvent( event: MotionEvent ) =
	{
		if( swipe ) super.onInterceptTouchEvent( event ) else false
	}
}