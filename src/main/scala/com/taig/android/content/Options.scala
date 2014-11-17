package com.taig.android.content

import android.support.v7.internal.view.SupportMenuInflater
import android.view.{MenuInflater, Menu}
import com.taig.android.content

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
			override def main = 0

			def split: Int

			override def inflate()
			{
				super.inflate()

				content.actionbar.split.map( _.getMenu ) match
				{
					case Some( menu ) => new SupportMenuInflater( context ).inflate( split, menu )
					case _ => content.actionbar.main.inflateMenu( split )
				}
			}

			override def find( id: Int ) = content.actionbar.split
				.map( _.getMenu.findItem( id ) )
				.flatMap( Option.apply )
				.getOrElse( super.find( id ) )
		}
	}

	trait	Property
	extends	content.Property[Options]
	{
		def main: Int

		def inflate()
		{
			if( main > 0 )
			{
				content.actionbar.main.inflateMenu( main )
			}
		}

		def find( id: Int ) = content.actionbar.main.getMenu.findItem( id )
	}
}