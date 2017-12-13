package io.taig.android.widget.operation

import android.support.v4.view.ViewPager

final class viewPager(viewPager: ViewPager) {
  def next(smoothly: Boolean = true) =
    viewPager.setCurrentItem(viewPager.getCurrentItem + 1)

  def previous(smoothly: Boolean = true): Unit =
    viewPager.setCurrentItem(viewPager.getCurrentItem - 1)
}
