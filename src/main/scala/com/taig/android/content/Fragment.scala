package com.taig.android.content

import android.support.v4.app.DialogFragment

trait Fragment extends android.support.v4.app.Fragment with Contextual
{
	override protected implicit lazy val context = getActivity
}

object Fragment
{
	trait Dialog extends DialogFragment with Fragment
	{
		override def onDestroyView()
		{
			if( getDialog != null && getRetainInstance )
			{
				getDialog.setDismissMessage( null )
			}

			super.onDestroyView()
		}
	}
}