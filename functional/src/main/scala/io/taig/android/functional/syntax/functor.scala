package io.taig.android.functional.syntax

import cats.Functor
import io.taig.android.functional.operation

import scala.language.{ higherKinds, implicitConversions }

trait functor {
    implicit def functorSyntax[A, F[_]: Functor]( functor: F[A] ): operation.functor[A, F] = {
        new operation.functor[A, F]( functor )
    }
}

object functor extends functor