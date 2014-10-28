package com.taig.android.view

import android.view.View.{LAYOUT_DIRECTION_LTR, LAYOUT_DIRECTION_RTL}

object View
{
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
}