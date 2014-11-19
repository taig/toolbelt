package com.taig.android.content.fragment

import com.taig.android.content
import com.taig.android.content.{Fragment, activity}

trait	ActionBar
extends Fragment
with	content.ActionBar
{
	def actionbar: ActionBar.Property
}

object ActionBar
{
	trait	Split
	extends	ActionBar
	with	content.ActionBar.Split
	{
		override def actionbar: Split.Property
	}

	object Split
	{
		trait	Property
		extends	content.Property[Split]
		with	ActionBar.Property
		with	content.ActionBar.Split.Property
		{
			override def split = content.getActivity.asInstanceOf[activity.ActionBar.Split].actionbar.split
		}
	}

	trait	Property
	extends	content.Property[ActionBar]
	with	content.ActionBar.Property
	{
		override def main = content.getActivity.asInstanceOf[activity.ActionBar].actionbar.main
	}
}