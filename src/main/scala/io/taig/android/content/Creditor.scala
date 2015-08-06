package io.taig.android.content

import scala.annotation.unchecked.uncheckedVariance
import scala.reflect._

/**
 * A Fragment may be a Creditor, forcing the hosting Activity to implement its contract
 */
trait Creditor[+C] extends Fragment {
    private var activity: android.app.Activity = null

    def tag: ClassTag[C @uncheckedVariance]

    override def onAttach( activity: android.app.Activity ) = {
        super.onAttach( activity )

        try {
            activity.getClass.getDeclaredMethod( tag.runtimeClass.getSimpleName )
        }
        catch {
            case _: NoSuchMethodException ⇒
                throw new IllegalStateException(
                    s"Activity ${activity.getClass.getName} did not implement contract ${tag.runtimeClass.getName}"
                )
        }

        this.activity = activity
    }

    override def onDetach() = {
        super.onDetach()

        this.activity = null
    }

    def ->> : C = {
        val name = tag.runtimeClass.getSimpleName
        val method = activity.getClass.getDeclaredMethod( name )
        method.invoke( activity ).asInstanceOf[C]
    }
}