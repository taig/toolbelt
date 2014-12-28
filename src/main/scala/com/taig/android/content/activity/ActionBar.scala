package com.taig.android.content.activity

import android.content.res.Configuration.ORIENTATION_PORTRAIT
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import com.taig.android._
import com.taig.android.content.{Activity, Property}
import com.taig.android.conversion._
import com.taig.android.util._
import com.taig.android.widget.ActionMenu

trait	ActionBar
extends	Activity
with	content.ActionBar
{
	override val actionbar = new Property( this ) with ActionBar.Property

	override def onCreate( state: Option[Bundle] )
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
		override val actionbar = new content.Property( this ) with Split.Property

		override def onCreate( state: Option[Bundle] )
		{
			super.onCreate( state )

			actionbar.split.foreach( setFooterView )
		}
	}

	object Split
	{
		trait	Property
		extends	content.Property[Split]
		with	ActionBar.Property
		with	content.ActionBar.Split.Property
		{
			override lazy val split: Option[ActionMenu] =
			{
				( context.getResources.getConfiguration.orientation == ORIENTATION_PORTRAIT ).asOption
				{
					content
						.getLayoutInflater
						.inflate( R.layout.bar_split, null )
						.asInstanceOf[ActionMenu]
				}
			}
		}
	}

	trait	Property
	extends	content.Property[ActionBar]
	with	content.ActionBar.Property
	{
		override lazy val main = content.getLayoutInflater.inflate( R.layout.bar_main, null ).asInstanceOf[Toolbar]
	}
}