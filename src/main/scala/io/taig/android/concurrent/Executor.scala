package io.taig.android.concurrent

import android.os.{AsyncTask, Handler, Looper}
import io.taig.android._

import scala.concurrent.ExecutionContext

object Executor
{
	/**
	 * Android's default asynchronous ExecutionContext
	 */
	implicit val Pool: ExecutionContext = ExecutionContext.fromExecutor( AsyncTask.THREAD_POOL_EXECUTOR )

	/**
	 * Ui-thread ExecutionContext
	 */
	val Ui: ExecutionContext = ExecutionContext.fromExecutor( new java.util.concurrent.Executor
	{
		private val handler = new Handler( Looper.getMainLooper )

		override def execute( command: Runnable ) = handler.post( command )
	} )

	/**
	 * Run on the Ui-Thread
	 */
	def Ui( body: => Unit ): Unit = Ui.execute( body )
}