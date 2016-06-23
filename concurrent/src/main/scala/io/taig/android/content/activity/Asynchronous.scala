package io.taig.android.content.activity

import io.taig.android.concurrent.Executor._
import io.taig.android.content._

import scala.concurrent.Future

trait Asynchronous extends Activity { self ⇒
    private lazy val helper = fragment.Asynchronous.Helper.findOrCreate( getFragmentManager )

    implicit protected class AsynchronousFuture[T]( future: Future[T] ) {
        def ui: AsyncApi[T, self.type] = new AsyncApi[T, self.type](
            future,
            // TODO not sure if this cast is actually legal
            helper.activity.asInstanceOf[self.type]
        )( helper.executor )
    }
}