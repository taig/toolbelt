package io.taig.android.content.fragment

import android.os.Bundle
import io.taig.android.concurrent.Ui
import io.taig.android.content.Fragment

import scala.collection.mutable

/**
 * A job queue that schedules its jobs for when the fragment is safe to use for Ui or transaction changes
 * 
 * All jobs will be automatically executed on the Ui thread
 */
trait	Jobs
extends	Fragment
{
	private var ready = false

	private lazy val queue = mutable.ListBuffer.empty[() => Unit]

	override def onCreate( state: Option[Bundle] ) =
	{
		super.onCreate( state )

		setRetainInstance( true )
	}

	override def onStart() = synchronized
	{
		super.onStart()

		ready = true

		Ui
		{
			queue.foreach( _() )
			queue.clear()
		}
	}

	override def onPause() = synchronized
	{
		super.onPause()

		ready = false
	}

	/**
	 * Do it now or as soon as the Fragment is (re-) starting
	 */
	def schedule( job: => Unit ) = synchronized( if( ready ) Ui( job ) else queue.append( () => job ) )

	/**
	 * Do it now or not at all
	 */
	def attempt( job: => Unit ) = synchronized( if( ready ) Ui( job ) )
}