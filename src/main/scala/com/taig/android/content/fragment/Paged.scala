package com.taig.android.content.fragment

import android.os.Bundle
import android.view.View
import com.taig.android.content.Fragment
import com.taig.android.content.activity.Pager

/**
 * Optional mixin to mark this fragment as page of a ViewPager providing additional lifecycle callbacks
 */
trait	Paged
extends	Fragment
{
	override def onViewCreated( view: View, state: Bundle )
	{
		super.onViewCreated( view, state )

		// Hack my way to initial event trigger
		context match
		{
			case activity: Pager if activity.pager.widget.getCurrentItem == 0 && activity.pager.adapter.getFragment( 0 ) == this =>
			{
				activity.pager.widget.triggerFocusListeners()
			}
			case _ => // Nothing to do
		}
	}

	/**
	 * Triggered when this fragment is used as the ViewPager's selection
	 */
	def onPagerFocused() {}

	/**
	 * Triggered when this fragment is no longer the ViewPager's selection
	 */
	def onPagerUnfocused() {}
}