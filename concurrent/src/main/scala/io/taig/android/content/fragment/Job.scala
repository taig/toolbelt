package io.taig.android.content.fragment

import android.os.Bundle
import io.taig.android._
import io.taig.android.content._
import io.taig.android.content.contract.Creditor
import monix.eval.Task
import monix.execution.Scheduler

import scala.language.{ existentials, implicitConversions, postfixOps }
import scala.util.{ Failure, Success, Try }

abstract class Job[T]( implicit s: Scheduler )
        extends Fragment
        with Asynchronous
        with Creditor[contract.Task[T]] {
    def before: Task[Unit] = Task.unit

    def task: Task[T]

    def job: Task[T] = before.flatMap( _ ⇒ task )

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