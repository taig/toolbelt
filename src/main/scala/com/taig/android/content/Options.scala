package com.taig.android.content

import android.support.v7.internal.view.SupportMenuInflater
import com.taig.android.content

trait	Options
extends	ActionBar
{
	def menu: Options.Property[Options]
}

object Options
{
	trait	Split
	extends	Options
	with	ActionBar.Split
	{
		override def menu: Split.Property[Split]
	}

	object Split
	{
		trait	Property[+S <: Split]
		extends	Options.Property[S]
		{
			override def action = 0

			def split: Int
		}

		object Property
		{
			trait	Split[+S <: Options.Split]
			extends	content.ActionBar.Split.Property[S]
			with	Options.Property.ActionBar[S]
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

	trait	Property[+O <: Options]
	extends	content.Property[O]
	{
		def action: Int
	}

	object Property
	{
		trait	ActionBar[+O <: Options]
		extends	content.ActionBar.Property[O]
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