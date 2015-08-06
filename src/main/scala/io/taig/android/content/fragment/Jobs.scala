package io.taig.android.content.fragment

import android.os.Bundle
import io.taig.android.concurrent.Executor.Ui
import io.taig.android.content.Fragment

import scala.collection.mutable
import scala.concurrent.ExecutionContext

/**
 * A job queue that schedules its jobs for when the fragment is safe to use for Ui or transaction changes
 *
 * All jobs will be automatically executed on the Ui thread
 */
trait Jobs
        extends Fragment {
    /**
     * Execution context for Fragment Ui operations
     *
     * Respects orientation changes and waits until the Fragment is ready for Ui operations
     */
    val Job = ExecutionContext.fromExecutor( Executor )

    private var ready = false

    override def onCreate( state: Option[Bundle] ) = {
        super.onCreate( state )

        setRetainInstance( true )
    }

    override def onStart() = {
        super.onStart()

        synchronized {
            ready = true
            Executor.workOff()
        }
    }

    override def onStop() = {
        super.onStop()

        synchronized {
            ready = false
        }
    }

    /**
     * Do it now or as soon as the Fragment is (re-) starting
     */
    def schedule( job: ⇒ Unit ) = Executor.runOrQueue( job )

    /**
     * Do it now or not at all
     */
    def attempt( job: ⇒ Unit ) = synchronized( if ( ready ) Ui( job ) )

    private object Executor
            extends java.util.concurrent.Executor {
        val queue = mutable.Queue.empty[() ⇒ Unit]

        /**
         * Execute all queued jobs
         */
        def workOff(): Unit = Jobs.this.synchronized {
            queue.dequeueAll( _ ⇒ true ).foreach( job ⇒ Ui( job() ) )
        }

        def runOrQueue( job: ⇒ Unit ) = Jobs.this.synchronized {
            if ( ready ) {
                Ui( job )
            }
            else {
                queue.enqueue( () ⇒ job )
            }
        }

        override def execute( command: Runnable ) = runOrQueue( command.run() )
    }
}