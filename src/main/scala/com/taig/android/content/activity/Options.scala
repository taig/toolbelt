package com.taig.android.content.activity

import android.view.MenuItem
import com.taig.android.content
import com.taig.android.content.{Activity, Property}
import com.taig.android.conversion._

trait	Options
extends	Activity
with	ActionBar
with	content.Options
{
	implicit def `Int -> Options.Property`( menu: Int ): Options.Property =
	{
		new Property( this ) with Options.Property
		{
			override def ids = Seq( menu )
		}
	}

	implicit def `Seq[Int] -> Options.Property`( menus: Seq[Int] ): Options.Property =
	{
		new Property( this ) with Options.Property
		{
			override def ids = menus
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
				override def ids = Seq( menu )
			}
		}

		override implicit def `Seq[Int] -> Options.Property`( menus: Seq[Int] ): Split.Property =
		{
			new content.Property( this ) with Split.Property
			{
				override def ids = menus
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
		{
			override def inflate()
			{
				super.inflate()

				content.actionbar.split.foreach
				{
					_.addOnMenuItemClickListener( content.onOptionsItemSelected( _: MenuItem ) )
				}
			}
		}
	}

	trait	Property
	extends	content.Property[Options]
	with	content.Options.Property
}