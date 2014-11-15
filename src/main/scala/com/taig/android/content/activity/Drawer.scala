package com.taig.android.content.activity

import android.os.Bundle
import android.view.{MenuItem, View}
import android.widget.FrameLayout
import com.taig.android.content._
import com.taig.android.content.activity.Drawer.Parameter
import com.taig.android.{R, content, widget}

trait	Drawer
extends	Activity
{
	require( !this.isInstanceOf[Back], "Can't use both: Drawer and Back, pick one!" )

	def drawer: Drawer.Property[Drawer]

	override def onCreate( state: Bundle )
	{
		super.onCreate( state )

		setRootView( R.layout.drawer )

		this match
		{
			// TODO load icon depending on dark or light theme
			case activity: ActionBar => activity.actionbar.main.setNavigationIcon( R.drawable.icon_light_hamburger )
			case _ => // Nothing to do here ...
		}

		// Prepare navigation drawer
		findViewById( R.id.drawer )
			.asInstanceOf[FrameLayout]
			.addView( drawer.widget )

		// Restore drawer state: opened or closed?
		Option( state ).collect
		{
			case state if state.getBoolean( Parameter.Drawer, false ) => drawer.wrapper.openDrawer( drawer.widget )
		}
	}

	/**
	 * Close navigation drawer when the back key is pressed
	 */
	override def onBackPressed() =
	{
		if( drawer.wrapper.isDrawerOpen( drawer.widget ) )
		{
			drawer.wrapper.closeDrawer( drawer.widget )
		}
		else
		{
			super.onBackPressed()
		}
	}

	override def onOptionsItemSelected( item: MenuItem ) = item.getItemId match
	{
		case android.R.id.home =>
		{
			if( drawer.wrapper.isDrawerOpen( drawer.widget ) )
			{
				drawer.wrapper.closeDrawer( drawer.widget )
			}
			else
			{
				drawer.wrapper.openDrawer( drawer.widget )
			}

			true
		}
		case _ => super.onOptionsItemSelected( item )
	}

	override def onSaveInstanceState( state: Bundle )
	{
		super.onSaveInstanceState( state )

		// Save drawer state: open or closed?
		state.putBoolean( Parameter.Drawer, drawer.wrapper.isDrawerOpen( drawer.widget ) )
	}
}

object Drawer
{
	val Parameter = new
	{
		val Drawer = getClass.getName + ".Drawer"
	}

	trait	Property[+D <: Drawer]
	extends	content.Property[Drawer]
	{
		def wrapper: com.taig.android.widget.Drawer

		def widget: View
	}
}