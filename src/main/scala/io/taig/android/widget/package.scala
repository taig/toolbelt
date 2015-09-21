package io.taig.android

import android.support.v4.view.ViewPropertyAnimatorCompat
import android.view.{ View, ViewGroup }
import android.widget.ViewSwitcher

package object widget {
    implicit class RichAnimation( animation: ViewPropertyAnimatorCompat )
        extends operation.Animation( animation )

    implicit class RichView( view: View )
        extends operation.View( view )

    implicit class RichViewGroup( val viewGroup: ViewGroup )
        extends operation.ViewGroup

    implicit class RichViewPagerGroup( val viewPager: android.support.v4.view.ViewPager )
        extends operation.ViewPager

    implicit class RichViewSwitcher( val viewSwitcher: ViewSwitcher )
        extends operation.ViewSwitcher
}