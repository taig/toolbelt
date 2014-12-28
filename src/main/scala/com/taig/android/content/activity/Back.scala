package com.taig.android.content.activity

import android.os.Bundle
import android.view.MenuItem
import com.taig.android.content.{Property, Activity}
import com.taig.android.content

trait	Back
extends	Activity
with	ActionBar
{
	require( !this.isInstanceOf[Drawer], "Can't use both: Drawer and Back, pick one!" )

	val back = new Property( this ) with Back.Property

	override def onCreate( state: Option[Bundle] )
	{
		super.onCreate( state )

		getSupportActionBar.setDisplayHomeAsUpEnabled( true )
	}

	override def onOptionsItemSelected( item: MenuItem ) = item.getItemId match
	{
		case android.R.id.home =>
		{
			back.onClick()
			true
		}
		case _ => super.onOptionsItemSelected( item )
	}
}

object Back
{
	trait	Property
	extends	content.Property[Back]
	{
		def onClick() = content.onBackPressed()
	}
}