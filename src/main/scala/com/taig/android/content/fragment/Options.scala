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
	override def actionbar = new Property( this ) with Options.Property.ActionBar[Options]

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
		override def actionbar = new content.Property( this ) with Split.Property.ActionBar[Split]

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
			trait	ActionBar[+S <: Split]
			extends	content.ActionBar.Split.Property[S]
			with	Options.Property.ActionBar[S]
			{
				override def split = content.getActivity.asInstanceOf[activity.ActionBar.Split].actionbar.split
			}
		}
	}

	object Property
	{
		trait	ActionBar[+O <: Options]
		extends	content.ActionBar.Property[O]
		{
			override def main = content.getActivity.asInstanceOf[activity.ActionBar].actionbar.main
		}
	}
}