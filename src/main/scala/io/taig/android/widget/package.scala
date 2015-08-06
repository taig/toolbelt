package io.taig.android

import android.view.{ View, ViewGroup }
import android.widget.ViewSwitcher

package object widget {
    implicit class RichView( val view: View )
        extends ops.View
        with ops.Animation

    implicit class RichViewGroup( val viewGroup: ViewGroup )
        extends ops.ViewGroup

    implicit class RichViewPagerGroup( val viewPager: android.support.v4.view.ViewPager )
        extends ops.ViewPager

    implicit class RichViewSwitcher( val viewSwitcher: ViewSwitcher )
        extends ops.ViewSwitcher
}