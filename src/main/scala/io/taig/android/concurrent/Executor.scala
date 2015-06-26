package io.taig.android.concurrent

import java.util.concurrent.Executor

import android.os.{Looper, Handler, AsyncTask}

import scala.concurrent.ExecutionContext

object Executor
{
	val Executor = new
		{
			/**
			 * Android's default asynchronous ExecutionContext
			 */
			implicit val Pool: ExecutionContext = ExecutionContext.fromExecutor( AsyncTask.THREAD_POOL_EXECUTOR )

			/**
			 * Ui-thread ExecutionContext
			 */
			val Ui: ExecutionContext = ExecutionContext.fromExecutor( new Executor
			{
				private val handler = new Handler( Looper.getMainLooper )

				override def execute( command: Runnable ) = handler.post( command )
			} )
		}

	/**
	 * Run in Ui-Thread
	 */
	def Ui( body: => Unit ) = Executor.Ui.execute( body )
}