package io.taig.android.content.fragment

import android.os.Bundle
import io.taig.android.content._
import io.taig.android._
import io.taig.android.content.contract.Creditor
import io.taig.android.extension.concurrent._

import scala.concurrent.{ ExecutionContext, Future }
import scala.language.{ existentials, implicitConversions, postfixOps }
import scala.util.{ Failure, Success, Try }

trait Task[T]
        extends Fragment
        with Jobs
        with Creditor[contract.Task[T]] {
    implicit def `( Future[T] ) => Future[Unit] => Future[T]`( future: Future[T] )(
        implicit
        ec: ExecutionContext
    ): Future[Unit] ⇒ Future[T] = {
        _.flatMap0( future )
    }

    def before = Future.successful( {} )

    def task: Future[Unit] ⇒ Future[T]

    final lazy val future = task( before )

    override def onCreate( state: Option[Bundle] ): Unit = {
        super.onCreate( state )

        future.onComplete {
            case result @ Success( value ) ⇒
                done( value )
                always( result )
            case result @ Failure( exception ) ⇒
                fail( exception )
                always( result )
        }( Job )
    }

    def done( value: T ) = {
        ->> {
            _.onSuccess.applyOrElse[T, Unit](
                value,
                result ⇒ fail( exception.UnexpectedResult( "Contract.onSuccess could not handle result", result ) )
            )
        }
    }

    def fail( exception: Throwable ): Unit = ->>( _.onFailure( exception ) )

    def always( result: Try[T] ): Unit = getFragmentManager.beginTransaction().remove( this ).commit()
}