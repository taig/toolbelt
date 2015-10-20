package io.taig.android.concurrent.operation

import scala.concurrent.ExecutionContext

abstract class Future[T]( future: scala.concurrent.Future[T] ) {
    /**
     * An alternative to map that does not provide an input argument
     */
    def map0[S]( f: ⇒ S )( implicit context: ExecutionContext ): scala.concurrent.Future[S] = {
        future.map( _ ⇒ f )
    }

    /**
     * An alternative to flatMap that does not provide an input argument
     */
    def flatMap0[S]( f: ⇒ scala.concurrent.Future[S] )( implicit context: ExecutionContext ): scala.concurrent.Future[S] = {
        future.flatMap( _ ⇒ f )
    }

    /**
     * Similar to map, but returns the previous future
     */
    def continue[U]( f: T ⇒ U )( implicit context: ExecutionContext ): scala.concurrent.Future[T] = {
        future.map( a ⇒ { f( a ); a } )
    }

    /**
     * An alternative to continue, but doesn't provide an input argument
     */
    def continue0[U]( f: ⇒ U )( implicit context: ExecutionContext ): scala.concurrent.Future[T] = {
        continue( _ ⇒ f )
    }

    /**
     * Similar to flatMap, but returns the previous future
     */
    def flatContinue[U]( f: T ⇒ scala.concurrent.Future[U] )( implicit context: ExecutionContext ): scala.concurrent.Future[T] = {
        future.flatMap( a ⇒ f( a ).map( _ ⇒ a ) )
    }

    /**
     * An alternative to flatContinue, but doesn't provide an input argument
     */
    def flatContinue0[U]( f: ⇒ scala.concurrent.Future[U] )( implicit context: ExecutionContext ): scala.concurrent.Future[T] = {
        flatContinue( _ ⇒ f )
    }
}