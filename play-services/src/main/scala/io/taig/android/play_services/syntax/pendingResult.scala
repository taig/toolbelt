package io.taig.android.play_services.syntax

import io.taig.android.play_services.operation
import com.google.android.gms.common.api.{ PendingResult, Result }

import scala.language.implicitConversions

trait pendingResult {
    implicit def playServicesPendingResultSyntax[R <: Result](
        result: PendingResult[R]
    ): operation.pendingResult[R] = new operation.pendingResult[R]( result )
}

object pendingResult extends pendingResult