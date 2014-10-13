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

	/**
	 * Flag a Fragment as Creditor of an Activity
	 * 
	 * If a Fragment is flagged with this trait, its hosting Activity has to implement the Contract C.
	 * 
	 * @tparam C The Contract that is implemented by the Activity
	 */
	trait Creditor[C <: Contract] extends content.Creditor[C]
	{
		this: Fragment =>

		override lazy val contract = context.asInstanceOf[C]
	}
}