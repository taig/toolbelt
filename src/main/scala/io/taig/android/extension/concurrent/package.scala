package io.taig.android.extension

import io.taig.android.concurrent.operation

import scala.concurrent.Future

package object concurrent {
    implicit class ToolbeltFuture[T]( future: Future[T] ) extends operation.Future[T]( future )
}