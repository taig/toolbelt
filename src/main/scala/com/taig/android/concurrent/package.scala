package com.taig.android

import android.os.AsyncTask

import scala.concurrent.ExecutionContext

package object concurrent
{
	implicit lazy val executionContext = ExecutionContext.fromExecutor( AsyncTask.THREAD_POOL_EXECUTOR )
}
