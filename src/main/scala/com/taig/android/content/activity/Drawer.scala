package com.taig.android.content.activity

import android.os.Bundle
import android.view.{MenuItem, View}
import android.widget.FrameLayout
import com.taig.android.content._
import com.taig.android.content.activity.Drawer.Parameter
import com.taig.android.{R, content}

trait	Drawer
extends	Activity
{
	require( !this.isInstanceOf[Back], "Can't use both: Drawer and Back, pick one!" )

	def drawer: Drawer.Property

	override def onCreate( state: Option[Bundle] )
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
		drawer.wrapper.addView( drawer.widget )

		// Restore drawer state: opened or closed?
		state
			.map( _.getBoolean( Parameter.Drawer, false ) )
			.collect{ case true => drawer.root.openDrawer( drawer.wrapper ) }
	}

	/**
	 * Close navigation drawer when the back key is pressed
	 */
	override def onBackPressed() =
	{
		if( drawer.root.isDrawerOpen( drawer.wrapper ) )
		{
			drawer.root.closeDrawer( drawer.wrapper )
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
			if( drawer.root.isDrawerOpen( drawer.wrapper ) )
			{
				drawer.root.closeDrawer( drawer.wrapper )
			}
			else
			{
				drawer.root.openDrawer( drawer.wrapper )
			}

			true
		}
		case _ => super.onOptionsItemSelected( item )
	}

	override def onSaveInstanceState( state: Bundle )
	{
		super.onSaveInstanceState( state )

		// Save drawer state: open or closed?
		state.putBoolean( Parameter.Drawer, drawer.root.isDrawerOpen( drawer.wrapper ) )
	}
}

object Drawer
{
	val Parameter = new
	{
		val Drawer = getClass.getName + ".Drawer"
	}

	trait	Property
	extends	content.Property[Drawer]
	{
		lazy val root = content
			.findViewById( R.id.drawer_root )
			.asInstanceOf[com.taig.android.widget.Drawer]

		lazy val wrapper = content
			.findViewById( R.id.drawer )
			.asInstanceOf[FrameLayout]

		/**
		 * The actual drawer layout
		 */
		def widget: View
	}
}