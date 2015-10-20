package io.taig.android.widget.operation

trait ViewPager {
    def viewPager: android.support.v4.view.ViewPager

    def next( smoothly: Boolean = true ) = viewPager.setCurrentItem( viewPager.getCurrentItem + 1 )

    def previous( smoothly: Boolean = true ): Unit = viewPager.setCurrentItem( viewPager.getCurrentItem - 1 )
}