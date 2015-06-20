package io.taig.android.content

import android.os.Bundle

trait	Activity
extends	android.app.Activity
with	Contextual
{
	override implicit def context: this.type = this

	private var initial = false

	private var lostFocus = false

	private var paused = false

	private var stopped = false

	/**
	 * Check whether this activity started from ground up or has been recreated after an orientation change
	 * 
	 * This method may only be called in [[onCreate()]] at the earliest
	 * 
	 * @return <code>true</code> after a fresh start, <code>false</code> after an orientation change
	 */
	def isInitialStart: Boolean = initial

	/**
	 * Check whether this activity has ever paused during its existence, not if it's currently paused
	 * 
	 * @return <code>true</code> if [[onPause()]] has ever been triggered, <code>false</code> otherwise
	 */
	def hasPaused: Boolean = paused

	/**
	 * Check whether this activity has ever stopped during its existence, not if it's currently stopped
	 *
	 * @return <code>true</code> if [[onStop()]] has ever been triggered, <code>false</code> otherwise
	 */
	def hasStopped: Boolean = paused

	/**
	 * Check whether this activity has ever lost focus during its existence, not if it's currently unfocused
	 *
	 * @return <code>true</code> if [[onWindowFocusChanged( hasFocus = false )]] has ever been triggered,
	 *         <code>false</code> otherwise
	 */
	def hasLostFocus: Boolean = lostFocus

	override protected final def onCreate( state: Bundle )
	{
		super.onCreate( state )

		onCreate( Option( state ) )
	}

	protected def onCreate( state: Option[Bundle] ): Unit =
	{
		initial = state.isEmpty
	}

	override protected final def onPostCreate( state: Bundle ) =
	{
		super.onPostCreate( state )

		onPostCreate( Option( state ) )
	}

	override def onWindowFocusChanged( hasFocus: Boolean ) =
	{
		super.onWindowFocusChanged( hasFocus )

		if( ! hasFocus )
		{
			lostFocus = true
		}
	}

	override def onPause() =
	{
		super.onPause()

		paused = true
	}

	override def onStop() =
	{
		super.onStop()

		stopped = true
	}

	protected def onPostCreate( state: Option[Bundle] ): Unit = {}
}