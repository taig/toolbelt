package io.taig.android.concurrent.app.activity

import io.taig.android.app.activity.Activity
import io.taig.android.concurrent.Executor._
import io.taig.android.concurrent.app._

import scala.concurrent.Future

trait Asynchronous extends Activity { self ⇒
  private lazy val helper =
    fragment.Asynchronous.Helper.findOrCreate(getFragmentManager)

  implicit protected class AsynchronousFuture[T](future: Future[T]) {
    def ui: AsyncApi[T, self.type] =
      new AsyncApi[T, self.type](
        future,
        // TODO not sure if this cast is actually legal
        helper.activity.asInstanceOf[self.type]
      )(helper.executor)
  }
}
