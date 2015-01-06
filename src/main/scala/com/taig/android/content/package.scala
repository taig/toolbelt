package com.taig.android

import android.content.res.Resources.NotFoundException
import android.content.{ContentResolver, Context}
import android.net.Uri
import android.view.LayoutInflater
import android.{content => android}

package object content
{
	implicit class RichContext( context: Context )
	{
		def inflater = LayoutInflater.from( context )

		def getExternalOrInternalCacheDir() = Option( context.getExternalCacheDir ).getOrElse( context.getCacheDir )

		/**
		 * Get the app's default PackageInfo
		 * 
		 * Useful to access manifest information, such as the app version.
		 * 
		 * @return Default PackageInfo
		 */
		def getPackageInfo() = context.getPackageManager.getPackageInfo( context.getPackageName, 0 )
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

		def asDimension( implicit context: Context ) = context.getResources.getDimension( resource )

		def asDrawable( implicit context: Context ) = context.getResources.getDrawable( resource )

		def asPixel( implicit context: Context ) = context.getResources.getDimensionPixelSize( resource )

		def asString( implicit context: Context ) = context.getString( resource )

		@throws[NotFoundException]
		def asUri( implicit context: Context ) = Uri.parse(
			ContentResolver.SCHEME_ANDROID_RESOURCE + "://" +
			context.getResources.getResourcePackageName( resource ) + "/" +
			context.getResources.getResourceTypeName( resource ) + "/" +
			context.getResources.getResourceEntryName( resource ) + "/"
		)
	}
}