package io.taig.android.widget.syntax

import android.view.ViewGroup
import io.taig.android.widget.operation

import scala.language.implicitConversions

trait viewGroup {
  implicit def viewGroupSyntax(viewGroup: ViewGroup): operation.viewGroup = {
    new operation.viewGroup(viewGroup)
  }
}

object viewGroup extends viewGroup
