package io.taig

import scala.language.implicitConversions

package object android
{
	implicit def `Any -> Runnable`[T]( any: => T ): Runnable = new Runnable { override def run() = any }
}