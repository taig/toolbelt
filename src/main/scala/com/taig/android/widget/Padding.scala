package com.taig.android.widget

import android.view.View._

case class Padding( left: Int, top: Int, right: Int, bottom: Int )
{
	def start( direction: Int ) = direction match
	{
		case LAYOUT_DIRECTION_LTR => left
		case LAYOUT_DIRECTION_RTL => right
	}

	def end( direction: Int ) = direction match
	{
		case LAYOUT_DIRECTION_LTR => right
		case LAYOUT_DIRECTION_RTL => left
	}
}