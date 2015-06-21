package io.taig.android

import android.view.{View, ViewGroup}
import android.widget.ViewSwitcher

package object widget
{
	implicit class	RichView( val view: View )
	extends			ViewOps

	implicit class	RichViewGroup( val viewGroup: ViewGroup )
	extends			ViewGroupOps

	implicit class	RichViewSwitcher( val viewSwitcher: ViewSwitcher )
	extends			ViewSwitcherOps
}