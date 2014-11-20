package com.taig.android.content.activity

import com.taig.android.content
import com.taig.android.content.{Activity, Property}

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
}

object Options
{
	trait	Split
	extends	Options
	with	ActionBar.Split
	with	content.Options.Split
	{
		override implicit def `Int -> Options.Property`( menu: Int ): Split.Property =
		{
			new content.Property( this ) with Split.Property
			{
				override def id = menu
			}
		}

		override def options: Split.Property
	}

	object Split
	{
		trait	Property
		extends	content.Property[Split]
		with	Options.Property
		with	content.Options.Split.Property
	}

	trait	Property
	extends	content.Property[Options]
	with	content.Options.Property
}