package com.taig.android.content.activity

import android.content.res.Configuration
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import com.taig.android._
import com.taig.android.content.{Activity, Property}
import com.taig.android.conversion._
import com.taig.android.widget.ActionMenu

trait	ActionBar
extends	Activity
with	content.ActionBar
{
	override lazy val actionbar = new Property( this ) with ActionBar.Property[ActionBar]

	override def onCreate( state: Bundle )
	{
		super.onCreate( state )

		setSupportActionBar( actionbar.main )
		setHeaderView( actionbar.main )
	}
}

object ActionBar
{
	trait	Split
	extends	ActionBar
	with	content.ActionBar.Split
	{
		override lazy val actionbar = new content.Property( this ) with Split.Property[Split]

		override def onCreate( state: Bundle )
		{
			super.onCreate( state )

			// Only create split bar when in portrait mode
			if( context.getResources.getConfiguration.orientation == Configuration.ORIENTATION_PORTRAIT )
			{
				val menu = getLayoutInflater.inflate( R.layout.bar_split, null ).asInstanceOf[ActionMenu]
				actionbar.split = Some( menu )
				setFooterView( menu )

				menu.addOnMenuItemClickListener( ( item: MenuItem ) => onOptionsItemSelected( item ) )
			}
		}
	}

	object Split
	{
		trait	Property[+S <: Split]
		extends	ActionBar.Property[S]
		with	content.ActionBar.Split.Property[S]
		{
			override var split: Option[ActionMenu] = None
		}
	}

	trait	Property[+A <: ActionBar]
	extends	content.ActionBar.Property[A]
	{
		override lazy val main = content.getLayoutInflater.inflate( R.layout.bar_main, null ).asInstanceOf[Toolbar]
	}
}