package com.taig.android.concurrent

import scala.concurrent.duration.Duration
import scala.concurrent.{CanAwait, ExecutionContext, TimeoutException}
import scala.language.reflectiveCalls
import scala.reflect.ClassTag
import scala.util.Try

class	Future[T] private( future: scala.concurrent.Future[T] )( implicit executor: ExecutionContext = Executor.Pool )
extends	scala.concurrent.Future[T]
{
	private def this( body: => T )( implicit executor: ExecutionContext ) = this( scala.concurrent.Future( body ) )

	override def onComplete[U]( f: ( Try[T] ) => U )( implicit executor: ExecutionContext = Executor.Ui ) =
	{
		future.onComplete( f )
	}

	override def isCompleted = future.isCompleted

	override def value = future.value

	@scala.throws[Exception]( classOf[Exception] )
	override def result( atMost: Duration )( implicit permit: CanAwait ) = future.result( atMost )

	@scala.throws[InterruptedException]( classOf[InterruptedException] )
	@scala.throws[TimeoutException]( classOf[TimeoutException] )
	override def ready( atMost: Duration )( implicit permit: CanAwait ) =
	{
		future.ready( atMost )
		this
	}

	override def onSuccess[U]( pf: PartialFunction[T, U] )( implicit executor: ExecutionContext = Executor.Ui ) =
	{
		future.onSuccess( pf )
	}

	override def onFailure[U]( pf: PartialFunction[Throwable, U] )( implicit executor: ExecutionContext = Executor.Ui ) =
	{
		future.onFailure( pf )
	}

	override def failed = new Future( future.failed )

	override def foreach[U]( f: ( T ) => U )( implicit executor: ExecutionContext = Executor.Ui ) = future.foreach( f )

	override def transform[S]( s: ( T ) => S, f: ( Throwable ) => Throwable )( implicit executor: ExecutionContext = Executor.Ui ) =
	{
		new Future( future.transform( s, f ) )
	}

	override def map[S]( f: ( T ) => S )( implicit executor: ExecutionContext = Executor.Ui ) =
	{
		new Future( future.map( f ) )
	}

	override def flatMap[S]( f: ( T ) => concurrent.Future[S] )( implicit executor: ExecutionContext = Executor.Ui ) =
	{
		new Future( future.flatMap( f ) )
	}

	override def filter( p: ( T ) => Boolean )( implicit executor: ExecutionContext = Executor.Ui ) =
	{
		new Future( future.filter( p ) )
	}

	override def collect[S]( pf: PartialFunction[T, S] )( implicit executor: ExecutionContext = Executor.Ui ) =
	{
		new Future( future.collect( pf ) )
	}

	override def recover[U >: T]( pf: PartialFunction[Throwable, U] )( implicit executor: ExecutionContext = Executor.Ui ) =
	{
		new Future( future.recover( pf ) )
	}

	override def recoverWith[U >: T]( pf: PartialFunction[Throwable, concurrent.Future[U]] )( implicit executor: ExecutionContext = Executor.Ui ) =
	{
		new Future( future.recoverWith( pf ) )
	}

	override def zip[U]( that: concurrent.Future[U] ) = new Future( future.zip( that ) )

	override def fallbackTo[U >: T]( that: concurrent.Future[U] ) = new Future( future.fallbackTo( that ) )

	override def mapTo[S]( implicit tag: ClassTag[S] ) = new Future( future.mapTo )

	override def andThen[U]( pf: PartialFunction[Try[T], U] )( implicit executor: ExecutionContext = Executor.Ui ) =
	{
		new Future( future.andThen( pf ) )
	}
}

object Future
{
	def apply[T]( body: => T )( implicit executor: ExecutionContext = Executor.Pool ) = new Future[T]( body )
}
