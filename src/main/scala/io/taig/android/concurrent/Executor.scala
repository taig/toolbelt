package io.taig.android.concurrent

import android.os.{AsyncTask, Handler, Looper}
import io.taig.android._

import scala.concurrent.{ExecutionContextExecutor, ExecutionContext}

object Executor
{
	/**
	 * Android's default asynchronous ExecutionContext
	 */
	implicit val Pool: ExecutionContextExecutor = ExecutionContext.fromExecutor( AsyncTask.THREAD_POOL_EXECUTOR )

	/**
	 * Ui-thread ExecutionContext
	 */
	val Ui = new ExecutionContext
	{
		private val handler = new Handler( Looper.getMainLooper )

		override def reportFailure( cause: Throwable ) = cause.printStackTrace()

		override def execute( command: Runnable ) = handler.post( command )
	}

	/**
	 * Run on the Ui-Thread
	 */
	def Ui( body: => Unit ): Unit = Ui.execute( body )
}