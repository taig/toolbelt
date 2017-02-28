package io.taig.android.monix.operation

import com.google.android.gms.common.api.{ PendingResult, Result, ResultCallback }
import monix.eval.Task
import monix.execution.Cancelable

final class task[+T]( task: Task[T] )

object task {
    final class companion( task: Task.type ) {
        def fromPendingResult[R <: Result](
            pending: ⇒ PendingResult[R]
        ): Task[R] = Task.create { ( _, callback ) ⇒
            pending.setResultCallback( new ResultCallback[R] {
                override def onResult( result: R ) = {
                    callback.onSuccess( result )
                }
            } )

            Cancelable( pending.cancel )
        }
    }
}