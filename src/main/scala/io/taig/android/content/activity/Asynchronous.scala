package io.taig.android.content.activity

import android.os.Bundle
import io.taig.android.concurrent.Executor._
import io.taig.android.content.activity.Asynchronous.Name
import io.taig.android.content.fragment.Fragment
import io.taig.android.util.Log

import scala.collection.mutable
import scala.concurrent.{ ExecutionContextExecutor, Future }

/**
 * A helper trait that mixes an enriched Future API into an Activity
 *
 * This way it's possible to make fluent use of the Scala Future API.
 *
 * {{{
 * Future( "asdf" )
 *     .map( _ ⇒ Thread.sleep( 3000 ) )
 *     .map( _ ⇒ 1000 )
 *     .ui( ( activity, result ) ⇒ activity.myTextView.setText( result.toString ) )
 * }}}
 */
trait Asynchronous extends Activity {
    private var helper: Asynchronous.Helper = null

    /**
     * Enrich the Future API by a method to interact with the Activity code
     */
    implicit class RichFuture[T]( future: Future[T] ) {
        def ui[U]( f: ( Asynchronous.this.type, T ) ⇒ U ): Unit = {
            future.foreach( t ⇒ f( helper.activity.asInstanceOf[Asynchronous.this.type], t ) )( helper.Executor )
        }

        def ui0[U]( f: Asynchronous.this.type ⇒ U ): Unit = ui( ( activity, _ ) ⇒ f( activity ) )
    }

    override def onCreate( state: Option[Bundle] ) = {
        super.onCreate( state )

        // Create or load executor helper fragment
        helper = Option {
            getFragmentManager
                .findFragmentByTag( Name )
                .asInstanceOf[Asynchronous.Helper]
        }.getOrElse {
            val executor = Asynchronous.Helper()

            getFragmentManager
                .beginTransaction()
                .add( executor, Name )
                .commit()

            executor
        }
    }
}

object Asynchronous {
    private val Name = classOf[Asynchronous].getName + ".executor"

    /**
     * Provides an ExecutionContext for an Asynchronous activity
     */
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
                Executor.workOff()
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
                Executor.queue.clear()
            }

            super.onDestroy()
        }

        object Executor extends ExecutionContextExecutor {
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
                Log.e( "Asynchronous Executor error", cause )( Log.Tag[Helper] )
            }
        }
    }

    object Helper {
        def apply() = new Helper
    }
}