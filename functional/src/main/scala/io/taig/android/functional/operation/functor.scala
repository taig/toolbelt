package io.taig.android.functional.operation

import cats.Functor
import cats.syntax.functor._

import scala.language.higherKinds

final class functor[A, F[_]: Functor](functor: F[A]) {
  @inline
  def continue[U](f: A ⇒ U): F[A] = functor.map { a ⇒
    f(a)
    a
  }

  @inline
  def continue0[U](value: ⇒ U): F[A] = functor.map { a ⇒
    value
    a
  }

  @inline
  def map0[B](value: ⇒ B): F[B] = functor.map(_ ⇒ value)
}
