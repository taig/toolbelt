package com.taig.android.content.activity

import android.os.Bundle
import android.support.v4.{app => support}
import com.taig.android._
import com.taig.android.content.{Property, Activity}

/**
 * An Activity that only hosts one single Fragment
 */
trait	Single
extends	Activity
with	Fragment
{
	protected implicit def `Class[Fragment] -> Single.Fragment`( fragment: Class[_ <: support.Fragment] ): Single.Fragment =
	{
		new Property( this ) with Single.Fragment
		{
			override def single = fragment
		}
	}

	override def fragment: Single.Fragment

	override def onCreate( state: Option[Bundle] )
	{
		super.onCreate( state )

		if( state.isEmpty )
		{
			getSupportFragmentManager
				.beginTransaction()
				.replace( R.id.content, fragment.instantiate( fragment.single ) )
				.commit()
		}
	}
}

object Single
{
	trait	Fragment
	extends	content.Property[Single]
	with	content.activity.Fragment.Property
	{
		override def all = Seq( single )

		def single: Class[_ <: support.Fragment]

		override def getActive() = content.getSupportFragmentManager.findFragmentById( R.id.content )
	}
}