package com.taig.android.content.fragment

import android.os.Bundle
import android.view.MenuItem
import com.taig.android.content
import com.taig.android.content.{Fragment, Property, activity}
import com.taig.android.conversion._

trait	Options
extends	Fragment
with	content.ActionBar
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

	override val actionbar = new Property( this ) with Options.ActionBar

	override def onCreate( state: Bundle )
	{
		super.onCreate( state )

		setHasOptionsMenu( true )
	}
}

object Options
{
	trait	Split
	extends	Options
	with	content.ActionBar.Split
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

		override val actionbar = new content.Property( this ) with Split.ActionBar
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

		trait	ActionBar
		extends	content.Property[Split]
		with	Options.ActionBar
		with	content.ActionBar.Split.Property
		{
			override def split = content.getActivity.asInstanceOf[activity.ActionBar.Split].actionbar.split
		}
	}

	trait	Property
	extends	content.Property[Options]
	with	content.Options.Property

	trait	ActionBar
	extends	content.Property[Options]
	with	content.ActionBar.Property
	{
		override def main = content.getActivity.asInstanceOf[activity.ActionBar].actionbar.main
	}
}