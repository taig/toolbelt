package com.taig.android.content.activity

import java.lang.reflect.InvocationTargetException

import android.support.v4.{app => support}
import com.taig.android.content
import com.taig.android.content.Activity
import com.taig.android.content.activity.Fragment.Property

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
		def all: Seq[Class[_ <: support.Fragment]]

		/**
		 * Get the Fragment that is currently considered as the Activity's main element
		 *
		 * @return Active Fragment
		 */
		def getActive(): support.Fragment

		def instantiate[F <: support.Fragment]( fragment: Class[F] ) =
		{
			try
			{
				fragment.getConstructor().newInstance()
			}
			catch
			{
				case e: InvocationTargetException => throw e.getCause
			}
		}
	}
}