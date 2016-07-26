package io.taig.android.concurrent.app.fragment

import android.os.Bundle
import android.util.Log
import io.taig.android.app.contract.Creditor
import io.taig.android.app.fragment.Fragment
import io.taig.android.concurrent.Executor.Pool
import io.taig.android.concurrent.app.contract
import io.taig.android.concurrent.{ Executor, exception }
import monix.eval.Task

import scala.util.{ Failure, Success, Try }

trait Job[T]
        extends Fragment
        with Asynchronous
        with Creditor[contract.Job[T]] {
    def before: Task[Unit] = Task.unit

    def task: Task[T]

    def job: Task[T] = before.flatMap( _ ⇒ task )

    override def onCreate( state: Option[Bundle] ): Unit = {
        super.onCreate( state )

        Log.wtf( "WTF", "About to run job ... or am I?" )

        job.runAsync
        //        job.runAsync.ui.onComplete {
        //            case ( task, result @ Success( value ) ) ⇒
        //                Log.wtf( "WTF", "Job succeeded" )
        //                //                task.done( value )
        //                //                task.always( result )
        //                Log.wtf( "WTF", "All callbacks handled. Goodybe" )
        //            case ( task, result @ Failure( exception ) ) ⇒
        //                Log.wtf( "WTF", "Job failed" )
        //                //                task.fail( exception )
        //                //                task.always( result )
        //                Log.wtf( "WTF", "All callbacks handled. Goodybe" )
        //        }
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