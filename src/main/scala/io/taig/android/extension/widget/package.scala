package io.taig.android.extension

import android.support.v4.view.ViewPropertyAnimatorCompat
import android.view.{ ViewGroup, View }
import android.widget.ViewSwitcher
import io.taig.android.widget.operation

package object widget {
    implicit class RichAnimation( animation: ViewPropertyAnimatorCompat ) extends operation.Animation( animation )

    implicit class RichView( view: View ) extends operation.View( view )

    implicit class RichViewGroup( viewGroup: ViewGroup ) extends operation.ViewGroup( viewGroup )

    implicit class RichViewPagerGroup( val viewPager: android.support.v4.view.ViewPager ) extends operation.ViewPager

    implicit class RichViewSwitcher( val viewSwitcher: ViewSwitcher ) extends operation.ViewSwitcher
}