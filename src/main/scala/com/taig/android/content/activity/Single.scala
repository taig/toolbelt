package com.taig.android.content.activity

import android.os.Bundle
import android.support.v4.{app => support}
import com.taig.android._
import com.taig.android.content.Activity

/**
 * An Activity that only hosts one single Fragment
 */
trait	Single
extends	Activity
with	Fragment
{
	override def fragment: Single.Property.Fragment[Single]

	override def onCreate( state: Bundle )
	{
		super.onCreate( state )

		if( state == null )
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
	object Property
	{
		trait	Fragment[+S <: Single]
		extends	content.activity.Fragment.Property[S]
		{
			override def all = Seq( single )

			def single: Class[_ <: support.Fragment]

			override def getActive() = content.getSupportFragmentManager.findFragmentById( R.id.content )
		}
	}
}