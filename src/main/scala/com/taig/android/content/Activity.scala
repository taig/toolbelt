package com.taig.android.content

import android.os.Bundle

trait	Activity
extends	android.app.Activity
with	Contextual
{
	override implicit def context: this.type = this

	override protected final def onCreate( state: Bundle )
	{
		super.onCreate( state )

		onCreate( Option( state ) )
	}

	protected def onCreate( state: Option[Bundle] ): Unit = {}

	override protected final def onPostCreate( state: Bundle ) =
	{
		super.onPostCreate( state )

		onPostCreate( Option( state ) )
	}

	protected def onPostCreate( state: Option[Bundle] ): Unit = {}
}