package io.taig.android.content

import java.lang.reflect.InvocationTargetException

import android.os.Bundle
import android.view.View

import scala.language.postfixOps

/**
 * A Fragment may be a Creditor, loosely forcing the hosting Activity to implement its contract
 */
trait Creditor[+C <: Contract] extends Fragment {
    private val name = getClass.getSimpleName

    private var target: Any = null

    override def onAttach( activity: android.app.Activity ) = {
        super.onAttach( activity )

        try {
            val method = activity.getClass.getDeclaredMethod( name )
            target = method.invoke( activity )
        } catch {
            case _: NoSuchMethodException | _: IllegalAccessException | _: IllegalArgumentException | _: InvocationTargetException ⇒
                throw new IllegalStateException(
                    s"Activity ${activity.getClass.getName} did not properly implement contract $name"
                )
        }

    }

    override def onViewCreated( view: View, state: Option[Bundle] ) = {
        super.onViewCreated( view, state )

        ->> onViewCreated
    }

    override def onStart() = {
        super.onStart()

        ->> onStart
    }

    override def onResume() = {
        super.onResume()

        ->> onResume
    }

    override def onStop() = {
        super.onStop()

        ->> onStop
    }

    override def onDetach() = {
        super.onDetach()

        this.target = null
    }

    def ->> : C = target.asInstanceOf[C]
}