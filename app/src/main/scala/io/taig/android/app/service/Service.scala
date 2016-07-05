package io.taig.android.app.service

import android.app.ActivityManager
import android.content.Context
import io.taig.android.context.Contextual
import io.taig.android.log.Log
import io.taig.android.system_service.syntax.context._

import scala.reflect._

trait Service
        extends android.app.Service
        with Contextual {
    override implicit def context = getApplicationContext
}

object Service {
    abstract class Companion[S <: android.app.Service: ClassTag] {
        implicit val tag: Log.Tag = Log.Tag[S]

        def isRunning( implicit c: Context ): Boolean = {
            import collection.JavaConversions._

            val target = classTag[S].runtimeClass.getName

            c.service[ActivityManager]
                .getRunningServices( Int.MaxValue )
                .exists( _.service.getClassName == target )
        }
    }
}