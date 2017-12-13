package io.taig.android.concurrent

import java.util.concurrent.TimeUnit

import android.os.AsyncTask
import com.google.android.gms.tasks.TaskExecutors
import io.taig.android.log.Log
import io.taig.android.util._
import monix.execution.Scheduler

import scala.concurrent.ExecutionContext

object Executor {

  /**
    * Pool thread Scheduler
    */
  implicit val Pool: Scheduler = Scheduler {
    ExecutionContext.fromExecutor(
      AsyncTask.THREAD_POOL_EXECUTOR,
      report(_, Log.Tag(Pool.getClass.getCanonicalName))
    )
  }

  /**
    * Single thread Scheduler
    */
  val Single: Scheduler = Scheduler {
    ExecutionContext.fromExecutor(
      AsyncTask.SERIAL_EXECUTOR,
      report(_, Log.Tag(Single.getClass.getCanonicalName))
    )
  }

  /**
    * Ui thread Scheduler
    */
  val Ui: Scheduler = Scheduler {
    ExecutionContext.fromExecutor(
      TaskExecutors.MAIN_THREAD,
      report(_, Log.Tag(Ui.getClass.getCanonicalName))
    )
  }

  /**
    * Run on the Ui-Thread
    */
  def Ui(body: ⇒ Unit): Unit = Ui.execute(() ⇒ body)

  def Ui(body: ⇒ Unit, delay: Long): Unit = {
    Ui.scheduleOnce(delay, TimeUnit.MILLISECONDS, () ⇒ body)
  }

  private[concurrent] def report(exception: Throwable, tag: Log.Tag): Unit = {
    Log.e("Failure during asynchronous operation", exception)(tag)
  }
}
