package io.taig.android.extension

import android.support.v4.view.ViewPropertyAnimatorCompat
import android.view.{ ViewGroup, View }
import android.widget.ViewSwitcher
import io.taig.android.widget.operation

package object widget {
    implicit class ToolbeltAnimation( animation: ViewPropertyAnimatorCompat ) extends operation.Animation( animation )

    implicit class ToolbeltView( view: View ) extends operation.View( view )

    implicit class ToolbeltViewGroup( viewGroup: ViewGroup ) extends operation.ViewGroup( viewGroup )

    implicit class ToolbeltViewPagerGroup( val viewPager: android.support.v4.view.ViewPager ) extends operation.ViewPager

    implicit class ToolbeltViewSwitcher( val viewSwitcher: ViewSwitcher ) extends operation.ViewSwitcher
}