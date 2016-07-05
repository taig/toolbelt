package io.taig.android

import scala.language.implicitConversions

package object util {
    implicit def fn1ToRunnable[U]( f: () ⇒ U ): Runnable = new Runnable { override def run() = f() }
}