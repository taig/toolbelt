package com.taig.android

import android.app.AlarmManager
import android.content.Context.ALARM_SERVICE
import android.content.{ContentResolver, Context}
import android.net.Uri
import android.view.{View, LayoutInflater}

import scala.reflect._

package object content
{
	implicit class RichActivity( activity: android.app.Activity )
	{
		/**
		 * Convenience wrapper for findViewById( id ).asInstanceOf[V]
		 */
		def find[V <: View]( id: Int ) = activity.findViewById( id ).asInstanceOf[V]
	}

	implicit class RichContext( context: Context )
	{
		def AlarmManger = context.getSystemService( ALARM_SERVICE ).asInstanceOf[AlarmManager]

		def Inflater = LayoutInflater.from( context )

		def getExternalOrInternalCacheDir() = Option( context.getExternalCacheDir ).getOrElse( context.getCacheDir )

		/**
		 * Get the app's default PackageInfo
		 * 
		 * Useful to access manifest information, such as the app version.
		 * 
		 * @return Default PackageInfo
		 */
		def getPackageInfo() = context.getPackageManager.getPackageInfo( context.getPackageName, 0 )

		def startActivity[A: ClassTag]: Unit = context.startActivity( Intent[A]( context, classTag[A] ) )
	}

	implicit class RichFragment( fragment: android.support.v4.app.Fragment )
	{
		/**
		 * Convenience wrapper for findViewById( id ).asInstanceOf[V]
		 */
		def find[V <: View]( id: Int ) = fragment.getView.findViewById( id ).asInstanceOf[V]
	}

	implicit class RichIntent( intent: android.content.Intent )( implicit context: Context )
	{
		/**
		 * Check if there is an activity available to handle this intent
		 * 
		 * @return <code>true</code> if this intent can be handled, <code>false</code> otherwise
		 */
		def canBeHandled = intent.resolveActivity( context.getPackageManager ) != null
	}

	implicit class RichUnit( unit: Float )( implicit context: Context )
	{
		import android.util.TypedValue._

		def dp = applyDimension( COMPLEX_UNIT_DIP, unit, context.getResources.getDisplayMetrics )

		def dip = dp

		def sp = applyDimension( COMPLEX_UNIT_SP, unit, context.getResources.getDisplayMetrics )
	}

	implicit class RichResource( resource: Int )( implicit context: Context )
	{
		def asBoolean = context.getResources.getBoolean( resource )

		def asColor = context.getResources.getColor( resource )

		def asDimension = context.getResources.getDimension( resource )

		def asDrawable = context.getResources.getDrawable( resource )

		def asInteger = context.getResources.getInteger( resource )

		def asIntegers = context.getResources.getIntArray( resource )

		def asPixel = context.getResources.getDimensionPixelSize( resource )

		def asString = context.getString( resource )

		def asStrings = context.getResources.getStringArray( resource )

		def asUri = Uri.parse(
			ContentResolver.SCHEME_ANDROID_RESOURCE + "://" +
			context.getResources.getResourcePackageName( resource ) + "/" +
			context.getResources.getResourceTypeName( resource ) + "/" +
			context.getResources.getResourceEntryName( resource ) + "/"
		)
	}
}