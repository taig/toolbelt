package com.taig.android.content

import android.support.v7.widget.Toolbar
import com.taig.android.content
import com.taig.android.widget.ActionMenu

trait	ActionBar
extends	Contextual
{
	def actionbar: ActionBar.Property[ActionBar]
}

object ActionBar
{
	trait	Split
	extends	ActionBar
	{
		override def actionbar: Split.Property[Split]
	}

	object Split
	{
		trait	Property[+S <: ActionBar.Split]
		extends	ActionBar.Property[S]
		{
			var split: Option[ActionMenu]
		}
	}

	trait	Property[+A <: ActionBar]
	extends	content.Property[A]
	{
		def main: Toolbar
	}
}