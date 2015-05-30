package com.taig.android

import java.util.concurrent.Executor

import android.os.{AsyncTask, Handler, Looper}
import com.taig.android.conversion._

import scala.concurrent.ExecutionContext
import scala.language.reflectiveCalls

package object concurrent
{
	lazy val Executor = new
	{
		implicit lazy val Pool = ExecutionContext.fromExecutor( AsyncTask.THREAD_POOL_EXECUTOR )

		lazy val Ui = ExecutionContext.fromExecutor( new Executor
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