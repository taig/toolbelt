package com.taig.android.content.activity

import android.view.Menu
import com.taig.android.content
import com.taig.android.content.{Property, Activity}

trait	Options
extends	Activity
with	ActionBar
with	content.Options
{
	implicit def `Int -> Options.Property`( menu: Int ): Options.Property =
	{
		new Property( this ) with Options.Property
		{
			override def id = menu
		}
	}

	override def options: Options.Property

	override def onCreateOptionsMenu( menu: Menu ) =
	{
		options.inflate()

		super.onCreateOptionsMenu( menu )
	}
}

object Options
{
	trait	Property
	extends	content.Property[Options]
	with	content.Options.Property
}