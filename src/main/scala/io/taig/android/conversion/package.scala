package io.taig.android

import android.annotation.TargetApi
import android.view.{View, ViewTreeObserver}

import scala.language.implicitConversions

package object conversion
{
	@TargetApi( 16 )
	implicit def `Function0 -> Unit -> ViewTreeObserver.OnDrawListener`( f: => Unit ) = new ViewTreeObserver.OnDrawListener()
	{
		override def onDraw() = f
	}

	implicit def `Function2 -> Unit -> ViewTreeObserver.OnGlobalFocusChangeListener`( f: ( View, View ) => Unit ) = new ViewTreeObserver.OnGlobalFocusChangeListener()
	{
		override def onGlobalFocusChanged( oldFocus: View, newFocus: View ) = f( oldFocus, newFocus )
	}

	implicit def `Function0 -> Unit -> ViewTreeObserver.OnGlobalLayoutListener`( f: => Unit ) = new ViewTreeObserver.OnGlobalLayoutListener()
	{
		override def onGlobalLayout() = f
	}

	implicit def `Function0 -> Boolean -> ViewTreeObserver.OnPreDrawListener`( f: => Boolean ) = new ViewTreeObserver.OnPreDrawListener()
	{
		override def onPreDraw() = f
	}

	implicit def `Function0 -> Unit -> ViewTreeObserver.OnScrollChangedListener`( f: => Unit ) = new ViewTreeObserver.OnScrollChangedListener()
	{
		override def onScrollChanged() = f
	}

	implicit def `Function1 -> Unit -> ViewTreeObserver.OnTouchModeChangeListener`( f: Boolean => Unit ) = new ViewTreeObserver.OnTouchModeChangeListener()
	{
		override def onTouchModeChanged( isInTouchMode: Boolean ) = f( isInTouchMode )
	}

	@TargetApi( 18 )
	implicit def `Function1 -> Unit -> ViewTreeObserver.OnWindowFocusChangeListener`( f: Boolean => Unit ) = new ViewTreeObserver.OnWindowFocusChangeListener()
	{
		override def onWindowFocusChanged( hasFocus: Boolean ) = f( hasFocus )
	}
}