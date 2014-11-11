package com.taig.android

import android.view.{ViewGroup, MenuItem, View}
import android.widget.ViewSwitcher

package object widget
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

		private def o = view.getViewTreeObserver

		def getPadding() = Padding( view.getPaddingLeft, view.getPaddingTop, view.getPaddingRight, view.getPaddingBottom )

		def setPadding( padding: Int ) = view.setPadding( padding, padding, padding, padding )

		def setPadding( vertical: Int, horizontal: Int ) = view.setPadding( horizontal, vertical, horizontal, vertical )

		def setPadding( padding: Padding ) = view.setPadding( padding.left, padding.top, padding.right, padding.bottom )

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
		def setOnNextWindowFocusChangeListener( listener: OnWindowFocusChangeListener )
		{
			addOnWindowFocusChangeListener( new OnWindowFocusChangeListener
			{
				override def onWindowFocusChanged( hasFocus: Boolean )
				{
					removeOnWindowFocusChangeListener( this )
					listener.onWindowFocusChanged( hasFocus )
				}
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
		def setOnNextGlobalFocusChangeListener( listener: OnGlobalFocusChangeListener )
		{
			addOnGlobalFocusChangeListener( new OnGlobalFocusChangeListener
			{
				override def onGlobalFocusChanged( oldFocus: View, newFocus: View )
				{
					removeOnGlobalFocusChangeListener( this )
					listener.onGlobalFocusChanged( oldFocus, newFocus )
				}
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
		def setOnNextGlobalLayoutListener( listener: OnGlobalLayoutListener )
		{
			addOnGlobalLayoutListener( new OnGlobalLayoutListener
			{
				override def onGlobalLayout()
				{
					removeOnGlobalLayoutListener( this )
					listener.onGlobalLayout()
				}
			} )
		}

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
		def setOnNextPreDrawListener( listener: OnPreDrawListener )
		{
			addOnPreDrawListener( new OnPreDrawListener
			{
				override def onPreDraw() =
				{
					removeOnPreDrawListener( this )
					listener.onPreDraw()
				}
			} )
		}

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
		def setOnNextDrawListener( listener: OnDrawListener )
		{
			addOnDrawListener( new OnDrawListener
			{
				override def onDraw()
				{
					removeOnDrawListener( this )
					listener.onDraw()
				}
			} )
		}

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
		def setOnNextScrollChangedListener( listener: OnScrollChangedListener )
		{
			addOnScrollChangedListener( new OnScrollChangedListener
			{
				override def onScrollChanged()
				{
					removeOnScrollChangedListener( this )
					listener.onScrollChanged()
				}
			} )
		}

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
			addOnTouchModeChangeListener( new OnTouchModeChangeListener
			{
				override def onTouchModeChanged( isInTouchMode: Boolean )
				{
					removeOnTouchModeChangeListener( this )
					listener.onTouchModeChanged( isInTouchMode )
				}
			} )
		}

		/**
		 * @see [[android.view.ViewTreeObserver#removeOnTouchModeChangeListener]]
		 */
		def removeOnTouchModeChangeListener( listener: OnTouchModeChangeListener ) = o.removeOnTouchModeChangeListener( listener )
	}

	implicit class RichViewGroup( view: ViewGroup )
	{
		/**
		 * Recursively discovers all children of this view and flattens them into a one dimensional collection
		 */
		def getAllChildren() =
		{
			def discover( view: View ): Seq[View] = view match
			{
				case group: ViewGroup => group +: discover( view )
				case _ => Seq( view )
			}

			discover( view )
		}
	}

	implicit class RichViewSwitcher( view: ViewSwitcher )
	{
		def toggle() = view.setDisplayedChild( ( view.getDisplayedChild + 1 ) % 2 )
	}
}