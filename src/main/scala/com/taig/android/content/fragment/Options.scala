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
	implicit def `Int -> Options.Property`( menu: Int ): Options.Property =
	{
		new Property( this ) with Options.Property
		{
			override def id = menu
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