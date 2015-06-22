package io.taig.android.widget.ops

import android.annotation.TargetApi
import android.os.Build.VERSION.SDK_INT
import io.taig.android.graphic.Direction._
import io.taig.android.graphic.Distance

trait View
{
	def view: android.view.View

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

	def setPadding( direction: Direction, padding: Int ) = direction match
	{
		case Left => view.setPadding( padding, view.getPaddingTop, view.getPaddingRight, view.getPaddingBottom )
		case Top => view.setPadding( view.getPaddingLeft, padding, view.getPaddingRight, view.getPaddingBottom )
		case Right => view.setPadding( view.getPaddingLeft, view.getPaddingTop, padding, view.getPaddingBottom )
		case Bottom => view.setPadding( view.getPaddingLeft, view.getPaddingTop, view.getPaddingRight, padding )
	}

	import android.view.ViewTreeObserver._

	private def o = view.getViewTreeObserver

	/**
	 * @see [[android.view.ViewTreeObserver#addOnWindowAttachListener]]
	 */
	@TargetApi( 18 )
	def addOnWindowAttachListener( listener: OnWindowAttachListener ) = o.addOnWindowAttachListener( listener )

	/**
	 * @see [[android.view.ViewTreeObserver#removeOnWindowAttachListener]]
	 */
	@TargetApi( 18 )
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
			override def onGlobalFocusChanged( oldFocus: android.view.View, newFocus: android.view.View )
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
		if( SDK_INT < 16 )
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