package io.taig.android.app.dialog

import android.support.v4.app.DialogFragment
import io.taig.android.app.fragment.Fragment

trait Dialog extends DialogFragment with Fragment {
  override def onDestroyView(): Unit = {
    if (getDialog != null && getRetainInstance) {
      getDialog.setDismissMessage(null)
    }

    super.onDestroyView()
  }
}
