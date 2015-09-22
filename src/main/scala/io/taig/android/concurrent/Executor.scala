package io.taig.android.concurrent

import android.os.{ AsyncTask, Handler, Looper }
import io.taig.android._
import io.taig.android.util.Log

import scala.concurrent.{ ExecutionContextExecutor, ExecutionContext }

object Executor {
    /**
     * Android's default asynchronous ExecutionContext
     */
    implicit val Pool: ExecutionContextExecutor = ExecutionContext.fromExecutor(
        AsyncTask.THREAD_POOL_EXECUTOR,
        cause ⇒ Log.e( cause.getMessage, cause )( Log.Tag( Pool.getClass.getName ) )
    )

    /**
     * Ui-thread ExecutionContext
     */
    val Ui: ExecutionContext = new ExecutionContext {
        val handler = new Handler( Looper.getMainLooper )

        override def reportFailure( cause: Throwable ) = {
            Log.e( cause.getMessage, cause )( Log.Tag( Ui.getClass.getName ) )
        }

        override def execute( command: Runnable ) = handler.post( command )

        def execute( command: Runnable, delay: Long ) = handler.postDelayed( command, delay )
    }

    /**
     * Run on the Ui-Thread
     */
    def Ui( body: ⇒ Unit ): Unit = Ui.execute( body )

    def Ui( body: ⇒ Unit, delay: Long ): Unit = Ui.execute( body, delay )
}