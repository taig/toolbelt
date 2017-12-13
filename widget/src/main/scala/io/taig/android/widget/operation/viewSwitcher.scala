package io.taig.android.widget.operation

import android.widget.ViewSwitcher

final class viewSwitcher(viewSwitcher: ViewSwitcher) {
  def toggle() =
    viewSwitcher.setDisplayedChild((viewSwitcher.getDisplayedChild + 1) % 2)
}
