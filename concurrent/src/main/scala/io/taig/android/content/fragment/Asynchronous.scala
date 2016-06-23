package io.taig.android.content.fragment

import android.os.Bundle
import io.taig.android.concurrent.Executor.Ui
import io.taig.android.content.AsyncApi
import io.taig.android.util.Log

import scala.collection.mutable
import scala.concurrent.{ ExecutionContext, Future }

trait Asynchronous extends Fragment { self ⇒
    implicit class AsynchronousFuture[T]( future: Future[T] ) {
        def ui: AsyncApi[T, self.type] = {
            if ( getRetainInstance ) {
                new AsyncApi[T, self.type]( future, self )( executor )
            } else {
                // TODO Use helper fragment approach
                ???
            }
        }
    }

    private var ready = false

    override def onCreate( state: Option[Bundle] ) = {
        super.onCreate( state )

        // TODO Might work without retaining as well?
        setRetainInstance( true )
    }

    override def onStart() = {
        super.onStart()

        synchronized {
            ready = true
            executor.workOff()
        }
    }

    override def onStop() = {
        super.onStop()

        synchronized {
            ready = false
        }
    }

    override def onDestroy() = {
        super.onDestroy()

        synchronized {
            executor.queue.clear()
        }
    }

    /**
     * Do it now or as soon as the Fragment is (re-) starting
     */
    def schedule( job: ⇒ Unit ) = executor.runOrQueue( job )

    /**
     * Do it now or not at all
     */
    def attempt( job: ⇒ Unit ) = synchronized( if ( ready ) Ui( job ) )

    private object executor extends ExecutionContext {
        val queue = mutable.Queue.empty[() ⇒ Unit]

        /**
         * Execute all queued jobs
         */
        def workOff(): Unit = Asynchronous.this.synchronized {
            queue.dequeueAll( _ ⇒ true ).foreach( job ⇒ Ui( job() ) )
        }

        def runOrQueue( job: ⇒ Unit ) = Asynchronous.this.synchronized {
            if ( ready ) {
                Ui( job )
            } else {
                queue.enqueue( () ⇒ job )
            }
        }

        override def execute( command: Runnable ) = runOrQueue( command.run() )

        override def reportFailure( exception: Throwable ) = {
            Log.e( "Asynchronous Fragment computation failed", exception )
        }
    }
}