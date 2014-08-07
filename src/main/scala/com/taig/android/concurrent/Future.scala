package com.taig.android.concurrent

import android.os.{AsyncTask, Handler, Looper}
import com.taig.android.conversion._

import scala.collection.mutable
import scala.concurrent.duration.Duration
import scala.concurrent.{CanAwait, ExecutionContext, TimeoutException}
import scala.util.Try

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

	override def onComplete[U]( f: ( Try[T] ) => U )( implicit executor: ExecutionContext = null ) = synchronized
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

	override def onSuccess[U]( pf: PartialFunction[T, U] )( implicit executor: ExecutionContext = null ) = super.onSuccess( pf )

	override def onFailure[U]( pf: PartialFunction[Throwable, U] )( implicit executor: ExecutionContext = null ) = super.onFailure( pf )
	
	override def foreach[U]( f: ( T ) => U )( implicit executor: ExecutionContext = null ) = super.foreach( f )

	override def transform[S]( s: ( T ) => S, f: ( Throwable ) => Throwable )( implicit executor: ExecutionContext = null ) = super.transform( s, f )

	override def map[S]( f: ( T ) => S )( implicit executor: ExecutionContext = null ) = super.map( f )

	override def flatMap[S]( f: ( T ) => concurrent.Future[S] )( implicit executor: ExecutionContext = null ) = super.flatMap( f )

	override def filter( p: ( T ) => Boolean )( implicit executor: ExecutionContext = null ) = super.filter( p )

	override def collect[S]( pf: PartialFunction[T, S] )( implicit executor: ExecutionContext = null ) = super.collect( pf )

	override def recover[U >: T]( pf: PartialFunction[Throwable, U] )( implicit executor: ExecutionContext = null ) = super.recover( pf )

	override def recoverWith[U >: T]( pf: PartialFunction[Throwable, concurrent.Future[U]] )( implicit executor: ExecutionContext = null ) = super.recoverWith( pf )

	override def andThen[U]( pf: PartialFunction[Try[T], U] )( implicit executor: ExecutionContext = null ) = super.andThen( pf )

	override def isCompleted = result.isDefined

	override def value = result

	@scala.throws[InterruptedException]( classOf[InterruptedException] )
	@scala.throws[TimeoutException]( classOf[TimeoutException] )
	override def ready( atMost: Duration )( implicit permit: CanAwait ) = throw new NotImplementedError()

	@scala.throws[Exception]( classOf[Exception] )
	override def result( atMost: Duration )( implicit permit: CanAwait ) = throw new NotImplementedError()
}

object Future
{
	private lazy val handler = new Handler( Looper.getMainLooper )

	def apply[T]( body: => T ) = new Future[T]( body )
}
