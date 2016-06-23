package io.taig.android.widget.operation

trait ViewSwitcher {
    def viewSwitcher: android.widget.ViewSwitcher

    def toggle() = viewSwitcher.setDisplayedChild( ( viewSwitcher.getDisplayedChild + 1 ) % 2 )
}