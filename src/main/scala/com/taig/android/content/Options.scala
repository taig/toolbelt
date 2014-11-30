package com.taig.android.content

import android.support.v7.internal.view.SupportMenuInflater
import android.support.v7.internal.view.menu.{MenuBuilder, MenuItemImpl}
import com.taig.android.content
import com.taig.android.widget._

trait	Options
extends	ActionBar
{
	def options: Options.Property
}

object Options
{
	trait	Split
	extends	Options
	with	ActionBar.Split
	{
		override def options: Split.Property
	}

	object Split
	{
		trait	Property
		extends	content.Property[Split]
		with	Options.Property
		{
			override def inflate() = content.actionbar.split match
			{
				case Some( split ) =>
				{
					val menu = new MenuBuilder( context )
					ids.foreach( new SupportMenuInflater( context ).inflate( _, menu ) )

					val items = new
					{
						val ( split, main ) = ( 0 to menu.size() - 1 )
							.map( menu.getItem )
							.collect{ case item: MenuItemImpl => item }
							.partition( _.requiresActionButton() )
					}

					items.main.foreach( content.actionbar.main.getMenu.add( _ ) )
					items.split.foreach( split.getMenu.add( _ ) )
				}
				case None => super.inflate()
			}

			override def find( id: Int ) = content.actionbar.split
				.flatMap( split => Option( split.getMenu.findItem( id ) ) )
				.getOrElse( super.find( id ) )

			override def clear()
			{
				super.clear()
				content.actionbar.split.foreach( _.clear() )
			}
		}
	}

	trait	Property
	extends	content.Property[Options]
	{
		protected implicit def `Int -> Seq[Int]`( id: Int ): Seq[Int] = Seq( id )

		def ids: Seq[Int]

		def inflate() = ids.foreach( content.actionbar.main.inflateMenu )

		def find( id: Int ) = content.actionbar.main.getMenu.findItem( id )

		def clear() = content.actionbar.main.getMenu.clear()
	}
}