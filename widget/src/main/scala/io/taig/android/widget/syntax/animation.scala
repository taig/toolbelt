package io.taig.android.widget.syntax

import android.support.v4.view.ViewPropertyAnimatorCompat
import io.taig.android.widget.operation

import scala.language.implicitConversions

trait animation {
  implicit def animationSyntax(
      animation: ViewPropertyAnimatorCompat): operation.animation = {
    new operation.animation(animation)
  }
}

object animation extends animation
