package io.taig.android.widget.syntax

import android.widget.ViewSwitcher
import io.taig.android.widget.operation

import scala.language.implicitConversions

trait viewSwitcher {
  implicit def viewSwitcherSyntax(
      viewSwitcher: ViewSwitcher): operation.viewSwitcher = {
    new operation.viewSwitcher(viewSwitcher)
  }
}

object viewSwitcher extends viewSwitcher
