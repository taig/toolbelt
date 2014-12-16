package com.taig.android.content.fragment

/**
 * Optional mixin to mark this fragment as page of a ViewPager
 */
trait Paged
{
	def onPagerFocused() {}

	def onPagerUnfocused() {}
}