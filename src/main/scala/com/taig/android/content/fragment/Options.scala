package com.taig.android.content.fragment

import android.os.Bundle
import android.view.MenuItem
import com.taig.android.content.{Property, Fragment, activity}
import com.taig.android.content
import com.taig.android.conversion._

trait	Options
extends	Fragment
with	content.ActionBar
with	content.Options
{
	override def actionbar = new Property( this ) with Options.Property.ActionBar

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
		override def actionbar = new content.Property( this ) with Split.Property.ActionBar

		override def onCreate( state: Bundle )
		{
			super.onCreate( state )

			actionbar.split.foreach
			{
				_.addOnMenuItemClickListener( ( item: MenuItem ) => onOptionsItemSelected( item ) )
			}
		}
	}

	object Split
	{
		object Property
		{
			trait	ActionBar
			extends	content.Property[Split]
			with	content.ActionBar.Split.Property
			with	Options.Property.ActionBar
			{
				override def split = content.getActivity.asInstanceOf[activity.ActionBar.Split].actionbar.split
			}
		}
	}

	object Property
	{
		trait	ActionBar
		extends	content.Property[Options]
		with	content.ActionBar.Property
		{
			override def main = content.getActivity.asInstanceOf[activity.ActionBar].actionbar.main
		}
	}
}