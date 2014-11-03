package com.taig.android

import android.content.Context
import android.view.LayoutInflater
import android.{content => android}

package object content
{
	implicit class RichContext( context: Context )
	{
		def inflater = LayoutInflater.from( context )

		def getExternalOrInternalCacheDir = Option( context.getExternalCacheDir ).getOrElse( context.getCacheDir )
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

	implicit class RichResource( resource: Int )
	{
		def asColor( implicit context: Context ) = context.getResources.getColor( resource )

		def asDrawable( implicit context: Context ) = context.getResources.getDrawable( resource )

		def asString( implicit context: Context ) = context.getString( resource )
	}
}