package io.taig.android.content

/**
 * A Fragment may be a Creditor, loosely forcing the hosting Activity to implement its contract
 */
trait Creditor[+C] extends Fragment {
    private var activity: android.app.Activity = null

    private val name = getClass.getSimpleName

    override def onAttach( activity: android.app.Activity ) = {
        super.onAttach( activity )

        try {
            activity.getClass.getDeclaredMethod( name )
            this.activity = activity
        }
        catch {
            case _: NoSuchMethodException ⇒
                throw new IllegalStateException(
                    s"Activity ${activity.getClass.getName} did not implement contract $name"
                )
        }

    }

    override def onDetach() = {
        super.onDetach()

        this.activity = null
    }

    def ->> : C = {
        val method = activity.getClass.getDeclaredMethod( name )
        method.invoke( activity ).asInstanceOf[C]
    }
}