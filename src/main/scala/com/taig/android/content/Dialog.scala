package com.taig.android.content

import android.app
import android.os.Bundle
import android.support.v4.app.DialogFragment

trait	Dialog
extends	DialogFragment
with	Fragment
{
	override final def onCreateDialog( state: Bundle ) =
	{
		super.onCreateDialog( state )

		onCreateDialog( Option( state ) )
	}

	def onCreateDialog( state: Option[Bundle] ): app.Dialog = new app.Dialog( getActivity, getTheme )

	override def onDestroyView()
	{
		if( getDialog != null && getRetainInstance )
		{
			getDialog.setDismissMessage( null )
		}

		super.onDestroyView()
	}
}