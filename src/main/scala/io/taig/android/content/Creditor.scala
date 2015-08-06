package io.taig.android.content

/**
 * A Fragment may be a Creditor, forcing the hosting Activity to implement its contract
 */
trait Creditor[C]
        extends Fragment {
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

    def debtor: C = activity.asInstanceOf[C]
}