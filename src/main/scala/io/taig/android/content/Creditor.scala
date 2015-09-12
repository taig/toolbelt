package io.taig.android.content

import java.lang.reflect.InvocationTargetException

/**
 * A Fragment may be a Creditor, loosely forcing the hosting Activity to implement its contract
 */
trait Creditor[+C] extends Fragment {
    private val name = getClass.getSimpleName

    private var target: Any = null

    override def onAttach( activity: android.app.Activity ) = {
        super.onAttach( activity )

        try {
            val method = activity.getClass.getDeclaredMethod( name )
            target = method.invoke( activity )
        }
        catch {
            case _: NoSuchMethodException | _: IllegalAccessException | _: IllegalArgumentException | _: InvocationTargetException ⇒
                throw new IllegalStateException(
                    s"Activity ${activity.getClass.getName} did not properly implement contract $name"
                )
        }

    }

    override def onDetach() = {
        super.onDetach()

        this.target = null
    }

    def ->> : C = target.asInstanceOf[C]
}