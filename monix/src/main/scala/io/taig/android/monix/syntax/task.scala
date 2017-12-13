package io.taig.android.monix.syntax

import io.taig.android.monix.operation
import monix.eval.Task

import scala.language.implicitConversions

trait task {
  implicit def monixTaskSyntax[T](
      task: Task[T]
  ): operation.task[T] = new operation.task[T](task)

  implicit def monixTaskCompanionSyntax(
      task: Task.type
  ): operation.task.companion = new operation.task.companion(task)
}

object task extends task
