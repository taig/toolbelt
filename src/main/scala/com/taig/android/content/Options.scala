package com.taig.android.content

import android.support.v7.internal.view.SupportMenuInflater
import com.taig.android.content

trait	Options
extends	ActionBar
{
	def menu: Options.Property
}

object Options
{
	trait	Split
	extends	Options
	with	ActionBar.Split
	{
		override def menu: Split.Property
	}

	object Split
	{
		trait	Property
		extends	content.Property[Split]
		with	Options.Property
		{
			override def action = 0

			def split: Int
		}

		object Property
		{
			trait	Split
			extends	content.Property[Options.Split]
			with	content.ActionBar.Split.Property
			with	Options.Property.ActionBar
			{
				override def inflate( id: Int ) = split match
				{
					case Some( split ) =>
					{
						new SupportMenuInflater( context ).inflate( content.menu.split, split.getMenu )
					}
					case _ => main.inflateMenu( content.menu.split )
				}

				override def find( id: Int ) = super.find( id )
			}
		}
	}

	trait	Property
	extends	content.Property[Options]
	{
		def action: Int
	}

	object Property
	{
		trait	ActionBar
		extends	content.Property[Options]
		with	content.ActionBar.Property
		{
			def inflate( id: Int )
			{
				if( id > 0 )
				{
					main.inflateMenu( id )
				}
			}

			def find( id: Int ) = main.getMenu.findItem( id )
		}
	}
}