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

	implicit def `Inflater -> View`[V <: View]( inflater: Inflater[V] ): V = inflater.view
}