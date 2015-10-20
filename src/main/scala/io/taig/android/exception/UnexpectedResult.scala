package io.taig.android.exception

import java.io.IOException

class UnexpectedResult[T]( val result: T, val cause: Throwable ) extends IOException( result.toString, cause )

object UnexpectedResult {
    def apply[T]( response: T ): UnexpectedResult[T] = new UnexpectedResult( response, null )

    def apply[T]( response: T, cause: Throwable ): UnexpectedResult[T] = new UnexpectedResult( response, cause )

    def unapply[T]( exception: UnexpectedResult[T] ) = Some( exception.result )
}