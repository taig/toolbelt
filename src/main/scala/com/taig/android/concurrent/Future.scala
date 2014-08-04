package com.taig.android.concurrent

import android.os.{AsyncTask, Handler, Looper}

import scala.concurrent.duration.Duration
import scala.concurrent.{CanAwait, ExecutionContext, TimeoutException}
import scala.util.Try
import com.taig.android._
import scala.collection.mutable

class Future[T]( body: => T ) extends scala.concurrent.Future[T]
{
	private var result: Option[Try[T]] = None

	private val tasks = new mutable.ListBuffer[( Try[T] ) => Any]

	AsyncTask.THREAD_POOL_EXECUTOR.execute(
	{
		result = Some( Try( body ) )

		Future.this.synchronized
		{
			Future.handler.post( tasks.foreach( _( result.get ) ) )
		}
	}: Unit )

	override def onComplete[U]( f: ( Try[T] ) => U )( implicit executor: ExecutionContext ) = synchronized
	{
		if( isCompleted )
		{
			f( result.get )
		}
		else
		{
			tasks.append( f )
		}
	}

	override def isCompleted = result.isDefined

	override def value = result

	@scala.throws[InterruptedException]( classOf[InterruptedException] )
	@scala.throws[TimeoutException]( classOf[TimeoutException] )
	override def ready( atMost: Duration )( implicit permit: CanAwait ) = null.asInstanceOf[this.type]

	@scala.throws[Exception]( classOf[Exception] )
	override def result( atMost: Duration )( implicit permit: CanAwait ) = null.asInstanceOf[T]
}

object Future
{
	private lazy val handler = new Handler( Looper.getMainLooper )

	def apply[T]( body: => T ) = new Future[T]( body )
}
