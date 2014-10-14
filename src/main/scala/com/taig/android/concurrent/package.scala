package com.taig.android

import java.util.concurrent.Executor

import android.os.{AsyncTask, Handler, Looper}

import scala.concurrent.ExecutionContext

package object concurrent
{
	lazy val Executor = new
	{
		implicit lazy val Pool = ExecutionContext.fromExecutor( AsyncTask.THREAD_POOL_EXECUTOR )

		implicit lazy val Ui = ExecutionContext.fromExecutor( new Executor
		{
			private val handler = new Handler( Looper.getMainLooper )

			override def execute( command: Runnable ) = handler.post( command )
		} )
	}
}