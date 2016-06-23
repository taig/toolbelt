package io.taig.android.content.fragment

import android.os.Bundle
import io.taig.android._
import io.taig.android.content._
import io.taig.android.content.contract.Creditor
import monix.eval.Task

import scala.language.{ existentials, implicitConversions, postfixOps }
import scala.util.{ Failure, Success, Try }

trait Job[T]
        extends Fragment
        with Asynchronous
        with Creditor[contract.Task[T]] {
    implicit def `( Task[T] ) => Task[Unit] => Task[T]`( task: Task[T] ): Task[Unit] ⇒ Task[T] = {
        _.flatMap( _ ⇒ task )
    }

    def before: Task[Unit] = Task.unit

    def task: Task[Unit] ⇒ Task[T]

    final lazy val job = task( before )

    implicit def scheduler = concurrent.Executor.Pool

    override def onCreate( state: Option[Bundle] ): Unit = {
        super.onCreate( state )

        job.runAsync.ui.onComplete {
            case ( task, result @ Success( value ) ) ⇒
                task.done( value )
                task.always( result )
            case ( task, result @ Failure( exception ) ) ⇒
                task.fail( exception )
                task.always( result )
        }
    }

    def done( value: T ) = ->> {
        _.onSuccess.applyOrElse[T, Unit](
            value,
            result ⇒ fail( exception.UnexpectedResult( "Contract.onSuccess could not handle result", result ) )
        )
    }

    def fail( exception: Throwable ): Unit = ->>( _.onFailure( exception ) )

    def always( result: Try[T] ): Unit = getFragmentManager.beginTransaction().remove( this ).commit()
}