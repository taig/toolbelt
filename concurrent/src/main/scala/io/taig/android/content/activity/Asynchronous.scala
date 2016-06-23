package io.taig.android.content.activity

import android.annotation.TargetApi
import android.os.Build.VERSION_CODES.M
import android.os.Bundle
import io.taig.android.concurrent.Executor._
import io.taig.android.content.AsyncApi
import io.taig.android.content.fragment.Fragment
import io.taig.android.util.Log

import scala.collection.mutable
import scala.concurrent.{ ExecutionContextExecutor, Future }

trait Asynchronous extends Activity { self ⇒
    private lazy val helper: Asynchronous.Helper = {
        Option {
            getFragmentManager
                .findFragmentByTag( classOf[Asynchronous.Helper].getCanonicalName )
                .asInstanceOf[Asynchronous.Helper]
        } getOrElse {
            val helper = Asynchronous.Helper()

            getFragmentManager
                .beginTransaction()
                .add( helper, classOf[Asynchronous.Helper].getCanonicalName )
                .commit()

            helper
        }
    }

    implicit class AsynchronousFuture[T]( future: Future[T] ) {
        def ui: AsyncApi[T, self.type] = new AsyncApi[T, self.type](
            future,
            // TODO not sure if this cast is actually legal
            helper.activity.asInstanceOf[self.type]
        )( helper.executor )
    }
}

object Asynchronous {
    /**
     * Provides an ExecutionContext for an Asynchronous activity
     */
    @TargetApi( M )
    class Helper extends Fragment {
        private var ready = false

        var activity: android.app.Activity = null

        override def onCreate( state: Option[Bundle] ) = {
            super.onCreate( state )

            setRetainInstance( true )
        }

        override def onAttach( activity: android.app.Activity ) = {
            super.onAttach( activity )

            this.activity = activity
        }

        override def onActivityCreated( state: Option[Bundle] ) = {
            super.onActivityCreated( state )

            synchronized {
                ready = true
                executor.workOff()
            }
        }

        override def onDetach() = {
            synchronized {
                ready = false
                this.activity = null
            }

            super.onDetach()

        }

        override def onDestroy() = {
            synchronized {
                executor.queue.clear()
            }

            super.onDestroy()
        }

        object executor extends ExecutionContextExecutor {
            val queue = mutable.Queue.empty[() ⇒ Unit]

            def workOff(): Unit = synchronized {
                queue.dequeueAll( _ ⇒ true ).foreach( job ⇒ Ui( job() ) )
            }

            def runOrQueue( job: ⇒ Unit ) = synchronized {
                if ( ready ) {
                    Ui( job )
                } else {
                    queue.enqueue( () ⇒ job )
                }
            }

            override def execute( command: Runnable ) = runOrQueue( command.run() )

            override def reportFailure( cause: Throwable ) = {
                Log.e( "Asynchronous executor error", cause )( Log.Tag[Helper] )
            }
        }
    }

    object Helper {
        def apply() = new Helper
    }
}