package io.taig.android.content

import android.app
import android.os.Bundle

trait	Dialog
extends	android.app.DialogFragment
with	Fragment
{
	override final def onCreateDialog( state: Bundle ) =
	{
		super.onCreateDialog( state )

		onCreateDialog( Option( state ) )
	}

	def onCreateDialog( state: Option[Bundle] ): app.Dialog = new app.Dialog( getActivity, getTheme )
}