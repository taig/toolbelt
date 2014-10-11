package com.taig.android

import android.view.{MenuItem, View}

package object view
{
	implicit class RichMenuItem( item: MenuItem )
	{
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

		def addOnWindowAttachListener( listener: OnWindowAttachListener ) = o.addOnWindowAttachListener( listener )

		def removeOnWindowAttachListener( listener: OnWindowAttachListener ) = o.removeOnWindowAttachListener( listener )

		def addOnWindowFocusChangeListener( listener: OnWindowFocusChangeListener ) = o.addOnWindowFocusChangeListener( listener )

		def removeOnWindowFocusChangeListener( listener: OnWindowFocusChangeListener ) = o.removeOnWindowFocusChangeListener( listener )

		def addOnGlobalFocusChangeListener( listener: OnGlobalFocusChangeListener ) = o.addOnGlobalFocusChangeListener( listener )

		def removeOnGlobalFocusChangeListener( listener: OnGlobalFocusChangeListener ) = o.removeOnGlobalFocusChangeListener( listener )

		def addOnGlobalLayoutListener( listener: OnGlobalLayoutListener ) = o.addOnGlobalLayoutListener( listener )

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

		def addOnPreDrawListener( listener: OnPreDrawListener ) = o.addOnPreDrawListener( listener )

		def removeOnPreDrawListener( listener: OnPreDrawListener ) = o.removeOnPreDrawListener( listener )

		def addOnDrawListener( listener: OnDrawListener ) = o.addOnDrawListener( listener )

		def removeOnDrawListener( listener: OnDrawListener ) = o.removeOnDrawListener( listener )

		def addOnScrollChangedListener( listener: OnScrollChangedListener ) = o.addOnScrollChangedListener( listener )

		def removeOnScrollChangedListener( listener: OnScrollChangedListener ) = o.removeOnScrollChangedListener( listener )

		def addOnTouchModeChangeListener( listener: OnTouchModeChangeListener ) = o.addOnTouchModeChangeListener( listener )

		def removeOnTouchModeChangeListener( listener: OnTouchModeChangeListener ) = o.removeOnTouchModeChangeListener( listener )
	}

	implicit def `Inflater -> View`[V <: View]( inflater: Inflater[V] ): V = inflater.view
}