package com.taig.android.content.fragment

import android.os.Bundle
import android.view.{MenuInflater, Menu, MenuItem}
import com.taig.android.content.{Property, Fragment, activity}
import com.taig.android.content
import com.taig.android.conversion._

trait	Options
extends	Fragment
with	content.ActionBar
with	content.Options
{
	implicit def `Int -> Options.Property`( id: Int ): Options.Property =
	{
		new Property( this ) with Options.Property
		{
			override def main = id
		}
	}

	override def actionbar = new Property( this ) with Options.ActionBar

	override def options: Options.Property

	override def onCreate( state: Bundle )
	{
		super.onCreate( state )

		setHasOptionsMenu( true )
	}

	override def onCreateOptionsMenu( menu: Menu, inflater: MenuInflater ) =
	{
		options.inflate()

		super.onCreateOptionsMenu( menu, inflater )
	}
}

object Options
{
	trait	Split
	extends	Options
	with	content.ActionBar.Split
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

		override def actionbar = new content.Property( this ) with Split.ActionBar

		override def options: Split.Property

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
		trait	Property
		extends	content.Property[Split]
		with	content.Options.Split.Property
		with	Options.Property

		trait	ActionBar
		extends	content.Property[Split]
		with	content.ActionBar.Split.Property
		with	Options.ActionBar
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