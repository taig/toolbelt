package io.taig.android.concurrent.app

import scala.concurrent.{ExecutionContext, Future}
import scala.util.{Failure, Success, Try}

class AsyncApi[T, C](future: Future[T], context: ⇒ C)(
    implicit ec: ExecutionContext) {
  def onSuccess[U](pf: PartialFunction[(C, T), U]): Unit = onComplete {
    case (context, Success(value)) ⇒
      pf.applyOrElse((context, value), identity[(C, T)])
    case _ ⇒ //
  }

  def onSuccessC[U](f: C ⇒ U): Unit = onSuccess {
    PartialFunction { case (context, _) ⇒ f(context) }
  }

  def onSuccessR[U](f: T ⇒ U): Unit = onSuccess {
    PartialFunction { case (_, result) ⇒ f(result) }
  }

  def onSuccess0[U](f: ⇒ U): Unit = onSuccess(PartialFunction(_ ⇒ f))

  def onFailure[U](pf: PartialFunction[(C, Throwable), U]): Unit = onComplete {
    case (context, Failure(exception)) ⇒
      pf.applyOrElse((context, exception), identity[(C, Throwable)])
    case _ ⇒ //
  }

  def onFailureC[U](f: C ⇒ U): Unit = onFailure {
    PartialFunction { case (context, _) ⇒ f(context) }
  }

  def onFailureR[U](f: Throwable ⇒ U): Unit = onFailure {
    PartialFunction { case (_, exception) ⇒ f(exception) }
  }

  def onFailure0[U](f: ⇒ U): Unit = onFailure {
    PartialFunction(_ ⇒ f)
  }

  def onComplete[U](f: (C, Try[T]) ⇒ U): Unit =
    future.onComplete(result ⇒ f(context, result))(ec)

  def onCompleteC[U](f: C ⇒ U): Unit = onComplete((context, _) ⇒ f(context))

  def onCompleteR[U](f: Try[T] ⇒ U): Unit = onComplete((_, result) ⇒ f(result))

  def onComplete0[U](f: ⇒ U): Unit = onComplete((_, _) ⇒ f)
}
