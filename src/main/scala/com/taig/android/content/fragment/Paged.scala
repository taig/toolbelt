package com.taig.android.content.fragment

import android.os.Bundle
import android.view.View
import com.taig.android.content.Fragment
import com.taig.android.content.activity.Pager

/**
 * Optional mixin to mark this fragment as page of a ViewPager
 */
trait	Paged
extends	Fragment
{
	override def onViewCreated( view: View, state: Bundle )
	{
		super.onViewCreated( view, state )

		context match
		{
			case activity: Pager if
				activity.pager.widget.getCurrentItem == 0 &&
				activity.pager.adapter.getItem( 0 ) == this =>
				{
					// Hack around to initial event trigger
					activity.pager.widget.triggerFocusListeners()
				}
			case _ => // Nothing to do
		}
	}

	def onPagerFocused() {}

	def onPagerUnfocused() {}
}