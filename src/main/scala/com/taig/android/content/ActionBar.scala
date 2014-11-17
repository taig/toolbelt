package com.taig.android.content

import android.support.v7.widget.Toolbar
import com.taig.android.content
import com.taig.android.widget.ActionMenu

trait	ActionBar
extends	Contextual
{
	def actionbar: ActionBar.Property
}

object ActionBar
{
	trait	Split
	extends	ActionBar
	{
		override def actionbar: Split.Property
	}

	object Split
	{
		trait	Property
		extends	content.Property[Split]
		with	ActionBar.Property
		{
			def split: Option[ActionMenu]
		}
	}

	trait	Property
	extends	content.Property[ActionBar]
	{
		def main: Toolbar
	}
}