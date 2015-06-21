package io.taig.android

import android.annotation.TargetApi
import android.os.Build
import android.view.{View, ViewGroup}
import android.widget.ViewSwitcher

package object widget
{
	implicit class RichView( view: View )
	{
		def getPadding() = Padding( view.getPaddingLeft, view.getPaddingTop, view.getPaddingRight, view.getPaddingBottom )

		def setPadding( padding: Int ) = view.setPadding( padding, padding, padding, padding )

		def setPadding( vertical: Int, horizontal: Int ) = view.setPadding( horizontal, vertical, horizontal, vertical )

		def setPadding( padding: widget.Padding ) = view.setPadding( padding.left, padding.top, padding.right, padding.bottom )

		def setPaddingBottom( padding: Int ) = view.setPadding( view.getPaddingLeft, view.getPaddingTop, view.getPaddingRight, padding )

		def setPaddingLeft( padding: Int ) = view.setPadding( padding, view.getPaddingTop, view.getPaddingRight, view.getPaddingBottom )

		def setPaddingRight( padding: Int ) = view.setPadding( view.getPaddingLeft, view.getPaddingTop, padding, view.getPaddingBottom )

		def setPaddingTop( padding: Int ) = view.setPadding( view.getPaddingLeft, padding, view.getPaddingRight, view.getPaddingBottom )

		import android.view.ViewTreeObserver._

		private def o = view.getViewTreeObserver

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
		@TargetApi( 18 )
		def addOnWindowFocusChangeListener( listener: OnWindowFocusChangeListener ) = o.addOnWindowFocusChangeListener( listener )

		/**
		 * Single execution listener that will automatically remove itself after the first execution
		 *
		 * @see addOnWindowFocusChangeListener
		 * @see removeOnWindowFocusChangeListener
		 * @see [[io.taig.android.conversion.`Function1 -> Unit -> ViewTreeObserver.OnWindowFocusChangeListener`()]]
		 */
		@TargetApi( 18 )
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
		@TargetApi( 18 )
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
		 * @see [[io.taig.android.conversion.`Function2 -> Unit -> ViewTreeObserver.OnGlobalFocusChangeListener`()]]
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
		 * @see [[io.taig.android.conversion.`Function0 -> Unit -> ViewTreeObserver.OnGlobalLayoutListener`()]]
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
			if( Build.VERSION.SDK_INT < 16 )
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
		 * @see [[io.taig.android.conversion.`Function0 -> Boolean -> ViewTreeObserver.OnPreDrawListener`()]]
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
		@TargetApi( 16 )
		def addOnDrawListener( listener: OnDrawListener ) = o.addOnDrawListener( listener )

		/**
		 * Single execution listener that will automatically remove itself after the first execution
		 *
		 * @see addOnDrawListener
		 * @see removeOnDrawListener
		 * @see [[io.taig.android.conversion.`Function0 -> Unit -> ViewTreeObserver.OnDrawListener`()]]
		 */
		@TargetApi( 16 )
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
		@TargetApi( 16 )
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
		 * @see [[io.taig.android.conversion.`Function0 -> Unit -> ViewTreeObserver.OnScrollChangedListener`()]]
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
		 * @see [[io.taig.android.conversion.`Function1 -> Unit -> ViewTreeObserver.OnTouchModeChangeListener`()]]
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

	implicit class RichViewGroup( viewGroup: ViewGroup )
	{
		/**
		 * Recursively discovers all children of this view and flattens them into a one dimensional collection in no
		 * particular order
		 */
		def children =
		{
			def discover( view: ViewGroup ): Seq[View] =
			{
				( 0 to view.getChildCount - 1 )
					.map( view.getChildAt ) match
					{
						case group: ViewGroup => group +: discover( group )
						case _ => Seq( view )
					}
			}

			discover( viewGroup )
		}

		/**
		 * Add view as first child
		 */
		def prependView( view: View ) = viewGroup.addView( view, 0 )

		/**
		 * Add view as first child
		 */
		def prependView( view: View, parameters: ViewGroup.LayoutParams ) = viewGroup.addView( view, 0, parameters )

		/**
		 * Add view as last child
		 */
		def appendView( view: View ) = viewGroup.addView( view, viewGroup.getChildCount - 1 )

		/**
		 * Add view as last child
		 */
		def appendView( view: View, parameters: ViewGroup.LayoutParams ) =
		{
			viewGroup.addView( view, viewGroup.getChildCount - 1, parameters )
		}
	}

	implicit class RichViewSwitcher( view: ViewSwitcher )
	{
		def toggle() = view.setDisplayedChild( ( view.getDisplayedChild + 1 ) % 2 )
	}
}