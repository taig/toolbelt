package io.taig.android.exception

import java.io.IOException

class UnexpectedResult[T]( val message: String, val result: T, val cause: Throwable )
    extends IOException( s"$message:\n$result", cause )

object UnexpectedResult {
    def apply[T]( message: String, response: T ): UnexpectedResult[T] = {
        new UnexpectedResult( message, response, null )
    }

    def apply[T]( message: String, response: T, cause: Throwable ): UnexpectedResult[T] = {
        new UnexpectedResult( message, response, cause )
    }

    def unapply[T]( exception: UnexpectedResult[T] ) = Some( exception.message, exception.result )
}