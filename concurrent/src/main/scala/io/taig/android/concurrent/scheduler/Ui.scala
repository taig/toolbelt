package io.taig.android.concurrent.scheduler

import java.util.concurrent.TimeUnit

import android.os.{ Handler, Looper }
import io.taig.android.concurrent.Executor
import io.taig.android.log.Log
import io.taig.android.util._
import monix.execution.ExecutionModel.SynchronousExecution
import monix.execution.{ Cancelable, ExecutionModel, Scheduler }

class Ui extends Scheduler {
    val handler = new Handler( Looper.getMainLooper )

    override def reportFailure( exception: Throwable ) = {
        Executor.report( exception, Log.Tag( getClass.getCanonicalName ) )
    }

    override def execute( runnable: Runnable ) = handler.post( runnable )

    override def currentTimeMillis() = System.currentTimeMillis()

    override val executionModel = SynchronousExecution

    override def scheduleOnce( delay: Long, unit: TimeUnit, runnable: Runnable ) = {
        handler.postDelayed( runnable, unit.toMillis( delay ) )
        Cancelable.empty
    }

    override def scheduleAtFixedRate(
        delay:    Long,
        period:   Long,
        unit:     TimeUnit,
        runnable: Runnable
    ) = {
        val delayMillis = unit.toMillis( delay )
        val periodMillis = unit.toMillis( period )

        var active = true

        handler.postDelayed(
            keepSchedulingAtFixedRate(
                runnable,
                periodMillis,
                () ⇒ active == false
            ),
            delayMillis
        )

        Cancelable( () ⇒ active = false )
    }

    def keepSchedulingAtFixedRate(
        runnable:   Runnable,
        period:     Long,
        isCanceled: () ⇒ Boolean
    ): Runnable = () ⇒ {
        if ( !isCanceled() ) {
            handler.postDelayed(
                keepSchedulingAtFixedRate( runnable, period, isCanceled ),
                period
            )
            runnable.run()
        }
    }

    override def scheduleWithFixedDelay(
        delay:    Long,
        period:   Long,
        unit:     TimeUnit,
        runnable: Runnable
    ) = {
        val delayMillis = unit.toMillis( delay )
        val periodMillis = unit.toMillis( period )

        var active = true

        handler.postDelayed(
            keepSchedulingWithFixedDelay(
                runnable,
                periodMillis,
                () ⇒ active == false
            ),
            delayMillis
        )

        Cancelable( () ⇒ active = false )
    }

    def keepSchedulingWithFixedDelay(
        runnable:   Runnable,
        period:     Long,
        isCanceled: () ⇒ Boolean
    ): Runnable = () ⇒ {
        if ( !isCanceled() ) {
            runnable.run()
            handler.postDelayed(
                keepSchedulingWithFixedDelay( runnable, period, isCanceled ),
                period
            )
        }
    }

    override def withExecutionModel( model: ExecutionModel ): Scheduler =
        new Ui
}
