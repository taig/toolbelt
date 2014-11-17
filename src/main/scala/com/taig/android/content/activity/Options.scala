package com.taig.android.content.activity

import android.view.Menu
import com.taig.android.content
import com.taig.android.content.{Property, Activity}

trait	Options
extends	Activity
with	ActionBar
with	content.Options
{
	implicit def `Int -> Options.Property`( id: Int ): Options.Property =
	{
		new Property( this ) with Options.Property
		{
			override def main = id
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
	trait	Split
	extends	Activity
	with	Options
	with	ActionBar.Split
	with	content.Options.Split
	{
		override implicit def `Int -> Options.Property`( id: Int ): Split.Property =
		{
			new content.Property( this ) with Split.Property
			{
				override def split = id
			}
		}

		implicit def `( Int, Int ) -> Options.Property`( ids: ( Int, Int ) ): Split.Property =
		{
			new content.Property( this ) with Split.Property
			{
				override def main = ids._1

				override def split = ids._2
			}
		}

		override def options: Split.Property
	}

	object Split
	{
		trait	Property
		extends	content.Property[Split]
		with	content.Options.Split.Property
		with	Options.Property
	}

	trait	Property
	extends	content.Property[Options]
	with	content.Options.Property
}