package io.taig.android.play_services.operation

import com.google.android.gms.common.api.{ PendingResult, Result, ResultCallback, Status }
import monix.eval.Task
import monix.execution.Cancelable

final class pendingResult[R <: Result]( result: PendingResult[R] ) {
    def toTask: Task[Status] = Task.create { ( _, callback ) ⇒
        result.setResultCallback( new ResultCallback[R] {
            override def onResult( result: R ) = {
                callback.onSuccess( result.getStatus )
            }
        } )

        Cancelable.empty
    }
}