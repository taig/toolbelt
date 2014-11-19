package com.taig.android.content

import android.support.v4.internal.view.SupportMenuItem.{SHOW_AS_ACTION_ALWAYS, SHOW_AS_ACTION_IF_ROOM, SHOW_AS_ACTION_WITH_TEXT}
import android.support.v7.internal.view.SupportMenuInflater
import android.support.v7.internal.view.menu.{MenuBuilder, MenuItemImpl}
import android.view.{Menu, MenuItem}
import com.taig.android.content

trait	Options
extends	ActionBar
{
	def options: Options.Property
}

object Options
{
	trait	Property
	extends	content.Property[Options]
	{
		def id: Int

		def inflate()
		{
			def addMenuItem( menu: Menu, item: MenuItem )
			{
				val newItem = menu.add(
					item.getGroupId,
					item.getItemId,
					item.getOrder,
					item.getTitle
				)

				newItem.setVisible( item.isVisible )

				( item, newItem ) match
				{
					case ( item: MenuItemImpl, newItem: MenuItemImpl ) =>
					{
						newItem.setShowAsActionFlags(
							( if( item.requestsActionButton() ) SHOW_AS_ACTION_IF_ROOM else 0 ) |
							( if( item.requiresActionButton() ) SHOW_AS_ACTION_ALWAYS else 0 ) |
							( if( item.showsTextAsAction() ) SHOW_AS_ACTION_WITH_TEXT else 0 )
						)
					}
					case _ => // Nothing to do here
				}
			}

			content match
			{
				case content: ActionBar.Split if content.actionbar.split.isDefined =>
				{
					// Split menu across main and split bar
					val menu = new MenuBuilder( context ) 
					new SupportMenuInflater( context ).inflate( id, menu )

					val ( split, main ) = ( 0 to menu.size() - 1 )
						.map( menu.getItem )
						.collect{ case item: MenuItemImpl => item }
						.partition( _.requiresActionButton() )

					main.foreach( addMenuItem( content.actionbar.main.getMenu, _ ) )
					split.foreach( addMenuItem( content.actionbar.split.get.getMenu, _ ) )
				}
				case _: ActionBar =>
				{
					// Everything goes into main bar
					content.actionbar.main.inflateMenu( id )
				}
				case _ => throw new RuntimeException( "Options trait can only be used with ActionBar" )
			}
		}

		def find( id: Int ) = content match
		{
			case content: ActionBar.Split if content.actionbar.split.isDefined =>
			{
				Option( content.actionbar.split.get.getMenu.findItem( id ) )
					.getOrElse( content.actionbar.main.getMenu.findItem( id ) )
			}
			case _: ActionBar =>
			{
				// Everything goes into main bar
				content.actionbar.main.getMenu.findItem( id )
			}
		}
	}
}