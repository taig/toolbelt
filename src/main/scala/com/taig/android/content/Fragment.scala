package com.taig.android.content

import android.support.v4.app.DialogFragment
import com.taig.android.content

trait Fragment extends android.support.v4.app.Fragment with Context
{
	override protected[content] implicit lazy val context = getActivity
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

	trait Creditor[C <: Contract] extends content.Creditor[C]
	{
		this: Fragment =>

		override lazy val contract = context.asInstanceOf[C]
	}
}