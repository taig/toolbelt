package com.taig.android.concurrent

import android.os.{AsyncTask, Handler, Looper}

import scala.concurrent.duration.Duration
import scala.concurrent.{CanAwait, ExecutionContext, TimeoutException}
import scala.util.Try

case class Future[+T]( body: => T ) extends scala.concurrent.Future[T]
{
	private var result: Option[Try[T]] = None

	AsyncTask.THREAD_POOL_EXECUTOR.execute(
	{
		result = Some( Try( body ) )
		Future.handler.post( onComplete( null ) )
	}: Unit )

	override def onComplete[U]( f: ( Try[T] ) => U )( implicit executor: ExecutionContext ): Unit = ???

	override def isCompleted = result.isDefined

	override def value = result

	@scala.throws[Exception]( classOf[Exception] )
	override def result( atMost: Duration )( implicit permit: CanAwait ): T = ???

	@scala.throws[InterruptedException]( classOf[InterruptedException] )
	@scala.throws[TimeoutException]( classOf[TimeoutException] )
	override def ready( atMost: Duration )( implicit permit: CanAwait ): Future[T] = ???
}

object Future
{
	private val handler = new Handler( Looper.getMainLooper )
}
