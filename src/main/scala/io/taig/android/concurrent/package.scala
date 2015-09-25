package io.taig.android

import scala.concurrent.Future

package object concurrent {
    implicit class RichFuture[T]( future: Future[T] ) extends operation.Future[T]( future )
}