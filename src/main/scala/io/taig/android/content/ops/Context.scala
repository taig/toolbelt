package io.taig.android.content.ops

import android.app.AlarmManager
import android.content.Context._
import android.os.PowerManager
import android.view.LayoutInflater
import io.taig.android.content

import scala.reflect._

trait Context
{
	def context: android.content.Context

	def AlarmManger = context.getSystemService( ALARM_SERVICE ).asInstanceOf[AlarmManager]

	def PowerService = context.getSystemService( POWER_SERVICE ).asInstanceOf[PowerManager]

	def LayoutInflater = context.getSystemService( LAYOUT_INFLATER_SERVICE ).asInstanceOf[LayoutInflater]

	def getExternalOrInternalCacheDir = Option( context.getExternalCacheDir ).getOrElse( context.getCacheDir )

	/**
	 * Get the app's default PackageInfo
	 *
	 * Useful to access manifest information, such as the app version.
	 *
	 * @return Default PackageInfo
	 */
	def getPackageInfo = context.getPackageManager.getPackageInfo( context.getPackageName, 0 )

	def startActivity[A: ClassTag]() = context.startActivity( content.Intent[A]( context, classTag[A] ) )
}