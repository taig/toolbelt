package com.taig.android

import android.view.{MenuItem, View}
import com.taig.android.conversion._

package object view
{
	implicit class RichMenuItem( item: MenuItem )
	{
		/**
		 * Adjusts icon transparency according to enabled state
		 * 
		 * By default, Android only changes the label's opacity and prevents any click interaction with the button.
		 * This method triggers the default [[MenuItem#setEnabled()]] method and also adjusts the icon's opacity.
		 * 
		 * @param enabled <code>true</code> to enable the MenuItem, <code>false</code> to disable
		 */
		def setEnabledWithIconState( enabled: Boolean )
		{
			Option( item.getIcon ).foreach( _.setAlpha( if( enabled ) 255 else 50 ) )

			item.setEnabled( enabled )
		}
	}

	implicit class RichView( view: View )
	{
		import android.view.ViewTreeObserver._

		private val o = view.getViewTreeObserver

		/**
		 * @see [[android.view.ViewTreeObserver#addOnWindowAttachListener]]
		 */
		def addOnWindowAttachListener( listener: OnWindowAttachListener ) = o.addOnWindowAttachListener( listener )

		/**
		 * @see [[android.view.ViewTreeObserver#removeOnWindowAttachListener]]
		 */
		def removeOnWindowAttachListener( listener: OnWindowAttachListener ) = o.removeOnWindowAttachListener( listener )

		/**
		 * @see [[android.view.ViewTreeObserver#addOnWindowFocusChangeListener]]
		 */
		def addOnWindowFocusChangeListener( listener: OnWindowFocusChangeListener ) = o.addOnWindowFocusChangeListener( listener )

		/**
		 * Single execution listener that will automatically remove itself after the first execution
		 *
		 * @see addOnWindowFocusChangeListener
		 * @see removeOnWindowFocusChangeListener
		 * @see [[com.taig.android.conversion.`Function1 -> Unit -> ViewTreeObserver.OnWindowFocusChangeListener`()]]
		 */
		def setOnNextWindowFocusChangeListener( listener: OnWindowFocusChangeListener ) =
		{
			addOnWindowFocusChangeListener( ( hasFocus: Boolean ) =>
			{
				removeOnWindowFocusChangeListener( listener )
				listener.onWindowFocusChanged( hasFocus )
			} )
		}

		/**
		 * @see [[android.view.ViewTreeObserver#removeOnWindowFocusChangeListener]]
		 */
		def removeOnWindowFocusChangeListener( listener: OnWindowFocusChangeListener ) = o.removeOnWindowFocusChangeListener( listener )

		/**
		 * @see [[android.view.ViewTreeObserver#addOnGlobalFocusChangeListener]]
		 */
		def addOnGlobalFocusChangeListener( listener: OnGlobalFocusChangeListener ) = o.addOnGlobalFocusChangeListener( listener )

		/**
		 * Single execution listener that will automatically remove itself after the first execution
		 *
		 * @see addOnGlobalFocusChangeListener
		 * @see removeOnGlobalFocusChangeListener
		 * @see [[com.taig.android.conversion.`Function2 -> Unit -> ViewTreeObserver.OnGlobalFocusChangeListener`()]]
		 */
		def setOnNextGlobalFocusChangeListener( listener: OnGlobalFocusChangeListener ) =
		{
			addOnGlobalFocusChangeListener( ( oldFocus: View, newFocus: View ) =>
			{
				removeOnGlobalFocusChangeListener( listener )
				listener.onGlobalFocusChanged( oldFocus, newFocus )
			} )
		}

		/**
		 * @see [[android.view.ViewTreeObserver#removeOnGlobalFocusChangeListener]]
		 */
		def removeOnGlobalFocusChangeListener( listener: OnGlobalFocusChangeListener ) = o.removeOnGlobalFocusChangeListener( listener )

		/**
		 * @see [[android.view.ViewTreeObserver#addOnGlobalLayoutListener]]
		 */
		def addOnGlobalLayoutListener( listener: OnGlobalLayoutListener ) = o.addOnGlobalLayoutListener( listener )

		/**
		 * Single execution listener that will automatically remove itself after the first execution
		 *
		 * @see addOnGlobalLayoutListener
		 * @see removeOnGlobalLayoutListener
		 * @see [[com.taig.android.conversion.`Function0 -> Unit -> ViewTreeObserver.OnGlobalLayoutListener`()]]
		 */
		def setOnNextGlobalLayoutListener( listener: OnGlobalLayoutListener ) = addOnGlobalLayoutListener(
		{
			removeOnGlobalLayoutListener( listener )
			listener.onGlobalLayout()
		}: Unit )

		/**
		 * @see [[android.view.ViewTreeObserver#removeOnGlobalLayoutListener]]
		 */
		def removeOnGlobalLayoutListener( listener: OnGlobalLayoutListener ) =
		{
			if( android.os.Build.VERSION.SDK_INT < 16 )
			{
				o.removeGlobalOnLayoutListener( listener )
			}
			else
			{
				o.removeOnGlobalLayoutListener( listener )
			}
		}

		/**
		 * @see [[android.view.ViewTreeObserver#addOnPreDrawListener]]
		 */
		def addOnPreDrawListener( listener: OnPreDrawListener ) = o.addOnPreDrawListener( listener )

		/**
		 * Single execution listener that will automatically remove itself after the first execution
		 *
		 * @see addOnPreDrawListener
		 * @see removeOnPreDrawListener
		 * @see [[com.taig.android.conversion.`Function0 -> Boolean -> ViewTreeObserver.OnPreDrawListener`()]]
		 */
		def setOnNextPreDrawListener( listener: OnPreDrawListener ) = addOnPreDrawListener(
		{
			removeOnPreDrawListener( listener )
			listener.onPreDraw()
		}: Boolean )

		/**
		 * @see [[android.view.ViewTreeObserver#removeOnPreDrawListener]]
		 */
		def removeOnPreDrawListener( listener: OnPreDrawListener ) = o.removeOnPreDrawListener( listener )

		/**
		 * @see [[android.view.ViewTreeObserver#addOnDrawListener]]
		 */
		def addOnDrawListener( listener: OnDrawListener ) = o.addOnDrawListener( listener )

		/**
		 * Single execution listener that will automatically remove itself after the first execution
		 *
		 * @see addOnDrawListener
		 * @see removeOnDrawListener
		 * @see [[com.taig.android.conversion.`Function0 -> Unit -> ViewTreeObserver.OnDrawListener`()]]
		 */
		def setOnNextDrawListener( listener: OnDrawListener ) = addOnDrawListener(
		{
			removeOnDrawListener( listener )
			listener.onDraw()
		}: Unit )

		/**
		 * @see [[android.view.ViewTreeObserver#removeOnDrawListener]]
		 */
		def removeOnDrawListener( listener: OnDrawListener ) = o.removeOnDrawListener( listener )

		/**
		 * @see [[android.view.ViewTreeObserver#addOnScrollChangedListener]]
		 */
		def addOnScrollChangedListener( listener: OnScrollChangedListener ) = o.addOnScrollChangedListener( listener )

		/**
		 * Single execution listener that will automatically remove itself after the first execution
		 *
		 * @see addOnScrollChangedListener
		 * @see removeOnScrollChangedListener
		 * @see [[com.taig.android.conversion.`Function0 -> Unit -> ViewTreeObserver.OnScrollChangedListener`()]]
		 */
		def setOnNextScrollChangedListener( listener: OnScrollChangedListener ) = addOnScrollChangedListener(
		{
			removeOnScrollChangedListener( listener )
			listener.onScrollChanged()
		}: Unit )

		/**
		 * @see [[android.view.ViewTreeObserver#removeOnScrollChangedListener]]
		 */
		def removeOnScrollChangedListener( listener: OnScrollChangedListener ) = o.removeOnScrollChangedListener( listener )

		/**
		 * @see [[android.view.ViewTreeObserver#addOnTouchModeChangeListener]]
		 */
		def addOnTouchModeChangeListener( listener: OnTouchModeChangeListener ) = o.addOnTouchModeChangeListener( listener )

		/**
		 * Single execution listener that will automatically remove itself after the first execution
		 *
		 * @see addOnTouchModeChangeListener
		 * @see removeOnTouchModeChangeListener
		 * @see [[com.taig.android.conversion.`Function1 -> Unit -> ViewTreeObserver.OnTouchModeChangeListener`()]]
		 */
		def setOnNextTouchModeChangeListener( listener: OnTouchModeChangeListener ) =
		{
			addOnTouchModeChangeListener( ( isInTouchMode: Boolean ) =>
			{
				removeOnTouchModeChangeListener( listener )
				listener.onTouchModeChanged( isInTouchMode )
			} )
		}

		/**
		 * @see [[android.view.ViewTreeObserver#removeOnTouchModeChangeListener]]
		 */
		def removeOnTouchModeChangeListener( listener: OnTouchModeChangeListener ) = o.removeOnTouchModeChangeListener( listener )
	}

	implicit def `Inflater -> View`[V <: View]( inflater: Inflater[V] ): V = inflater.view
}