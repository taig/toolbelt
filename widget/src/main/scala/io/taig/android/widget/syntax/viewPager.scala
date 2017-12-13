package io.taig.android.widget.syntax

import android.support.v4.view.ViewPager
import io.taig.android.widget.operation

import scala.language.implicitConversions

trait viewPager {
  implicit def viewPagerSyntax(viewPager: ViewPager): operation.viewPager = {
    new operation.viewPager(viewPager)
  }
}

object viewPager extends viewPager
