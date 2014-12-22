package com.taig.android.content

import android.app.ProgressDialog

import scala.concurrent.duration.Duration
import scala.concurrent.{ExecutionContext, CanAwait, TimeoutException, Future}
import scala.util.Try
import com.taig.android.{concurrent => taig}

/**
 * Fragment ProgressDialog as a Future
 * 
 * To execute multiple Futures after each other (which is a likely use case when interacting with Progress Dialogs)
 * they may be chained via [[Future.flatMap()]].
 * 
 * @param future Wrapped future object
 * @tparam T Result type of wrapped Future
 */
class	Dialog[T]( future: Future[T] )( implicit executor: ExecutionContext )
extends	Fragment.Dialog[ProgressDialog]
with	Future[T]
{
	/**
	 * Simple call by name constructor
	 * 
	 * The body routine will be wrapped by an [[com.taig.android.concurrent.Future]]
	 * ([[android.os.AsyncTask.THREAD_POOL_EXECUTOR]] parallel execution, Ui thread [[onComplete()]] execution)
	 * 
	 * @param body Routine to be wrapped by a Future
	 * @return Dialog fragment
	 */
	def this( body: => T )( implicit executor: ExecutionContext ) = this( taig.Future( body ) )

	override def onComplete[U]( f: ( Try[T] ) => U )( implicit executor: ExecutionContext )
	{
		dismiss()
		future.onComplete( f )
	}

	override def isCompleted = future.isCompleted

	override def value = future.value

	@throws[Exception]( classOf[Exception] )
	override def result( atMost: Duration )( implicit permit: CanAwait ) = future.result( atMost )

	@throws[InterruptedException]( classOf[InterruptedException] )
	@throws[TimeoutException]( classOf[TimeoutException] )
	override def ready( atMost: Duration )( implicit permit: CanAwait ) =
	{
		future.ready( atMost )
		this
	}
}