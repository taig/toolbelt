package com.taig.android.content.activity

import com.taig.android.content.Activity
import com.taig.android.content
import android.support.v4.{app => support}
import com.taig.android.content.activity.Fragment.Property

/**
 * Mark an Activity as a host of one or several Fragments
 *
 * This is supposed to be the default behavior and should therefore always be used with an Activity.
 */
trait	Fragment
extends	Activity
{
	def fragment: Property[Fragment]
}

object Fragment
{
	trait	Property[+F <: Fragment]
	extends	content.Property[F]
	{
		def all: Seq[Class[_ <: support.Fragment]]

		/**
		 * Get the Fragment that is currently considered as the Activity's main element
		 *
		 * @return Active Fragment
		 */
		def getActive(): support.Fragment

		def instantiate[F <: support.Fragment]( fragment: Class[F] ) = fragment.getConstructor().newInstance()
	}
}