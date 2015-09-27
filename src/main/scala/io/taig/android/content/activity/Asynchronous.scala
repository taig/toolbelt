package io.taig.android.content.activity

import android.os.Bundle
import io.taig.android.util.Log

import scala.concurrent.ExecutionContext
import scala.collection.mutable

trait Asynchronous extends Activity {
    override protected def onPostCreate( state: Option[Bundle] ) = {
        super.onPostCreate( state )

        Log.wtf( "Creating Activity: " + hashCode() )

        Asynchronous.attach( this )
    }

    override def onDestroy() = {
        if ( isFinishing ) {
            Asynchronous.pause( this )
        } else {
            Asynchronous.detach( this )
        }

        super.onDestroy()
    }
}

object Asynchronous extends ExecutionContext {
    private val queue = mutable.Queue.empty[( Int, Runnable )]

    private var current: Option[Activity] = None

    def attach( activity: Activity ): Unit = synchronized {
        current = Some( activity )
    }

    def pause( activity: Activity ): Unit = synchronized {
        current = None
    }

    def detach( activity: Activity ): Unit = synchronized {
        queue.dequeueAll{ case ( id, _ ) ⇒ id == activity.hashCode() }
        current = None
    }

    override def execute( runnable: Runnable ) = ???

    override def reportFailure( cause: Throwable ) = ???
}