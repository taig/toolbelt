package io.taig.android.content

import scala.reflect.ClassTag

/**
 * A Fragment may be a Creditor, forcing the hosting Activity to implement its contract
 */
trait Creditor[C]
        extends Fragment {
    def tag: ClassTag[C]

    private var activity: android.app.Activity = null

    // It would be wise to check if the Activity implements C in onActivityCreated(), but that would enforce us to
    // implement an implicit classTag field in the children. This might work out in the future with trait parameters.
    // Until then, please implement the fucking contract.

    override def onAttach( activity: android.app.Activity ) = {
        super.onAttach( activity )

        this.activity = activity
    }

    override def onDetach() = {
        super.onDetach()

        this.activity = null
    }

    @deprecated( "Use ->> instead", "0.4.1" )
    def debtor: C = activity.asInstanceOf[C]

    def ->> : C = {
        val method = tag.runtimeClass.getDeclaredField( tag.runtimeClass.getSimpleName )
        method.get( activity ).asInstanceOf[C]
    }
}