package io.taig.android.content.activity

import java.lang.reflect.InvocationTargetException

import io.taig.android.content
import io.taig.android.content.Activity
import io.taig.android.content.activity.Fragment.Property

/**
 * Mark an Activity as a host of one or several Fragments
 *
 * This is supposed to be the default behavior and should therefore always be used with an Activity.
 */
trait	Fragment
extends	Activity
{
	def fragment: Property
}

object Fragment
{
	trait	Property
	extends	content.Property[Fragment]
	{
		def all: Seq[Class[_ <: android.app.Fragment]]

		/**
		 * Get the Fragment that is currently considered as the Activity's main element
		 *
		 * @return Active Fragment
		 */
		def getActive(): android.app.Fragment

		def instantiate[F <: android.app.Fragment]( fragment: Class[F] ) =
		{
			try
			{
				fragment.getConstructor().newInstance()
			}
			catch
			{
				case exception: InvocationTargetException => throw exception.getCause
			}
		}
	}
}