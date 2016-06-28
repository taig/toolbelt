package io.taig.android.concurrent

import java.util.concurrent.TimeUnit

import android.os.{ AsyncTask, Handler, Looper }
import io.taig.android._
import io.taig.android.log.Log
import monix.execution.schedulers.ExecutionModel.SynchronousExecution
import monix.execution.{ Cancelable, Scheduler }

import scala.concurrent.ExecutionContext

object Executor {
    /**
     * Pool thread Scheduler
     */
    implicit val Pool: Scheduler = Scheduler {
        ExecutionContext.fromExecutor(
            AsyncTask.THREAD_POOL_EXECUTOR,
            exception ⇒ report( exception, Log.Tag( Pool.getClass.getName ) )
        )
    }

    /**
     * Single thread Scheduler
     */
    val Single: Scheduler = Scheduler {
        ExecutionContext.fromExecutor(
            AsyncTask.SERIAL_EXECUTOR,
            exception ⇒ report( exception, Log.Tag( Single.getClass.getName ) )
        )
    }

    /**
     * Ui thread Scheduler
     */
    val Ui: Scheduler = new Scheduler {
        val handler = new Handler( Looper.getMainLooper )

        override def reportFailure( exception: Throwable ) = report( exception, Log.Tag( Ui.getClass.getName ) )

        override def execute( runnable: Runnable ) = handler.post( runnable )

        override def currentTimeMillis() = System.currentTimeMillis()

        override val executionModel = SynchronousExecution

        override def scheduleOnce( delay: Long, unit: TimeUnit, runnable: Runnable ) = {
            handler.postDelayed( runnable, unit.toMillis( delay ) )
            Cancelable.empty
        }

        override def scheduleAtFixedRate( delay: Long, period: Long, unit: TimeUnit, runnable: Runnable ) = {
            val delayMillis = unit.toMillis( delay )
            val periodMillis = unit.toMillis( period )

            var active = true

            handler.postDelayed(
                keepSchedulingAtFixedRate( runnable, periodMillis, active == false ),
                delayMillis
            )

            Cancelable( () ⇒ active = false )
        }

        def keepSchedulingAtFixedRate( runnable: Runnable, period: Long, isCanceled: ⇒ Boolean ): Runnable = () ⇒ {
            if ( !isCanceled ) {
                handler.postDelayed( keepSchedulingAtFixedRate( runnable, period, isCanceled ), period )
                runnable.run()
            }
        }

        override def scheduleWithFixedDelay( delay: Long, period: Long, unit: TimeUnit, runnable: Runnable ) = {
            val delayMillis = unit.toMillis( delay )
            val periodMillis = unit.toMillis( period )

            var active = true

            handler.postDelayed(
                keepSchedulingWithFixedDelay( runnable, periodMillis, active == false ),
                delayMillis
            )

            Cancelable( () ⇒ active = false )
        }

        def keepSchedulingWithFixedDelay( runnable: Runnable, period: Long, isCanceled: ⇒ Boolean ): Runnable = () ⇒ {
            if ( !isCanceled ) {
                runnable.run()
                handler.postDelayed( keepSchedulingWithFixedDelay( runnable, period, isCanceled ), period )
            }
        }
    }

    /**
     * Run on the Ui-Thread
     */
    def Ui( body: ⇒ Unit ): Unit = Ui.execute( body )

    def Ui( body: ⇒ Unit, delay: Long ): Unit = Ui.scheduleOnce( delay, TimeUnit.MILLISECONDS, body )

    private def report( exception: Throwable, tag: Log.Tag ): Unit = {
        Log.e( "Failure during asynchronous operation", exception )( tag )
    }
}