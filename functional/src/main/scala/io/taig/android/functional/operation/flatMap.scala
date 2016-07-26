package io.taig.android.functional.operation

import cats.FlatMap
import cats.syntax.flatMap._
import cats.syntax.functor._

import scala.language.higherKinds

final class flatMap[A, F[_]: FlatMap]( flatMap: F[A] ) {
    @inline
    def flatContinue[U]( f: A ⇒ F[U] ): F[A] = {
        flatMap.flatMap { a ⇒ f( a ).map( _ ⇒ a ) }
    }

    @inline
    def flatContinue0[U]( value: ⇒ F[U] ): F[A] = {
        flatMap.flatMap( a ⇒ value.map( _ ⇒ a ) )
    }

    @inline
    def flatMap0[B]( value: ⇒ F[B] ): F[B] = {
        flatMap.flatMap( _ ⇒ value )
    }
}