package com.taig.android.content.activity

import android.os.Bundle
import android.view.MenuItem
import com.taig.android.content.Activity
import com.taig.android.content

trait	Back
extends	Activity
with	content.ActionBar
{
	require( !this.isInstanceOf[Drawer], "Can't use both: Drawer and Back, pick one!" )

	override def actionbar: Back.Property

	override def onCreate( state: Bundle )
	{
		super.onCreate( state )

		getSupportActionBar.setDisplayHomeAsUpEnabled( true )
	}

	override def onOptionsItemSelected( item: MenuItem ) = item.getItemId match
	{
		case android.R.id.home =>
		{
			actionbar.onBackPressed()
			true
		}
		case _ => super.onOptionsItemSelected( item )
	}
}

object Back
{
	trait	Property
	extends	content.Property[Back]
	with	content.ActionBar.Property
	{
		def onBackPressed(): Unit
	}
}