package io.taig.android.concurrent.app.fragment

import android.app.FragmentManager
import android.os.Bundle
import io.taig.android.app.fragment.Fragment
import io.taig.android.concurrent.Executor.Ui
import io.taig.android.concurrent.app.AsyncApi
import io.taig.android.log.Log

import scala.collection.mutable
import scala.concurrent.{ExecutionContext, ExecutionContextExecutor, Future}

trait Asynchronous extends Fragment { self ⇒
  implicit protected class AsynchronousFuture[T](future: Future[T]) {
    def ui: AsyncApi[T, self.type] =
      new AsyncApi[T, self.type](future, self)(executor)
  }

  private var ready = false

  override def onCreate(state: Bundle) = {
    super.onCreate(state)

    // TODO make this work without retaining as well!
    setRetainInstance(true)
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
  def schedule(job: ⇒ Unit) = executor.runOrQueue(job)

  /**
    * Do it now or not at all
    */
  def attempt(job: ⇒ Unit) = synchronized(if (ready) Ui(job))

  private object executor extends ExecutionContext {
    val queue = mutable.Queue.empty[() ⇒ Unit]

    /**
      * Execute all queued jobs
      */
    def workOff(): Unit = Asynchronous.this.synchronized {
      queue.dequeueAll(_ ⇒ true).foreach(job ⇒ Ui(job()))
    }

    def runOrQueue(job: ⇒ Unit) = Asynchronous.this.synchronized {
      if (ready) {
        Ui(job)
      } else {
        queue.enqueue(() ⇒ job)
      }
    }

    override def execute(command: Runnable) = runOrQueue(command.run())

    override def reportFailure(exception: Throwable) = {
      Log.e("Asynchronous Fragment computation failed", exception)
    }
  }
}

object Asynchronous {
  private[android] class Helper extends Fragment {
    private var ready = false

    var activity: android.app.Activity = null

    override def onCreate(state: Bundle) = {
      super.onCreate(state)

      setRetainInstance(true)
    }

    override def onAttach(activity: android.app.Activity) = {
      super.onAttach(activity)

      this.activity = activity
    }

    override def onActivityCreated(state: Bundle) = {
      super.onActivityCreated(state)

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
        queue.dequeueAll(_ ⇒ true).foreach(job ⇒ Ui(job()))
      }

      def runOrQueue(job: ⇒ Unit) = synchronized {
        if (ready) {
          Ui(job)
        } else {
          queue.enqueue(() ⇒ job)
        }
      }

      override def execute(command: Runnable) = runOrQueue(command.run())

      override def reportFailure(cause: Throwable) = {
        Log.e("Asynchronous executor error", cause)(Log.Tag[Helper])
      }
    }
  }

  private[android] object Helper {
    def findOrCreate(manager: FragmentManager) = {
      Option {
        manager
          .findFragmentByTag(classOf[Helper].getCanonicalName)
          .asInstanceOf[Helper]
      } getOrElse {
        val helper = new Helper()

        manager
          .beginTransaction()
          .add(helper, classOf[Helper].getCanonicalName)
          .commit()

        helper
      }
    }
  }
}
