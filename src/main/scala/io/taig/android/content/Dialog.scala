package io.taig.android.content

import android.os.Bundle

trait Dialog
        extends android.app.DialogFragment
        with Fragment {
    override final def onCreateDialog( state: Bundle ) = onCreateDialog( Option( state ) )

    def onCreateDialog( state: Option[Bundle] ): android.app.Dialog = new android.app.Dialog( getActivity, getTheme )

    override def onDestroyView() = {
        if ( getDialog != null && getRetainInstance ) {
            getDialog.setDismissMessage( null )
        }

        super.onDestroyView()
    }
}