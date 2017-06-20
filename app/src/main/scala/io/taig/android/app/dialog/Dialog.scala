package io.taig.android.app.dialog

import io.taig.android.app.fragment.Fragment

trait Dialog
        extends android.app.DialogFragment
        with Fragment {
    override def onDestroyView() = {
        if ( getDialog != null && getRetainInstance ) {
            getDialog.setDismissMessage( null )
        }

        super.onDestroyView()
    }
}