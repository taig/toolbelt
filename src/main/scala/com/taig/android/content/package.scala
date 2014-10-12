package com.taig.android

import android.{content => android}

package object content
{
	implicit class RichContext( value: android.Context ) extends Context
	{
		override protected[content] implicit val context = value
	}

	implicit class RichIntent( intent: android.Intent )
	{
		/**
		 * Check if there is an activity available to handle this intent
		 * 
		 * @return <code>true</code> if this intent can be handled, <code>false</code> otherwise
		 */
		def canBeHandled( implicit context: Context ) = intent.resolveActivity( context.getPackageManager ) != null
	}

	implicit def `Context -> Android.Context`( context: Context ): android.Context = context.context
}