package io.taig.android.widget

import android.widget.ViewSwitcher

trait ViewSwitcherOps
{
	def viewSwitcher: ViewSwitcher

	def toggle() = viewSwitcher.setDisplayedChild( ( viewSwitcher.getDisplayedChild + 1 ) % 2 )
}