package com.taig.android.content

import android.support.v4.app.{FragmentActivity, DialogFragment, ListFragment}
import com.taig.android.content

trait	Fragment
extends	android.support.v4.app.Fragment
with	Contextual
{
	// Manually store context, as it is mysteriously getting lost otherwise
	implicit override def context = Option( activity ).getOrElse( getActivity )

	private var activity: FragmentActivity = null

	override def onAttach( activity: android.app.Activity )
	{
		super.onAttach( activity )

		this.activity = activity match
		{
			case activity: FragmentActivity => activity
			case _ => null
		}
	}

	override def onDetach()
	{
		super.onDetach()

		this.activity = null
	}

	def findViewById( id: Int ) = getView.findViewById( id )
}

object Fragment
{
	trait	Dialog
	extends	DialogFragment
	with	Fragment
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

	trait	List
	extends	ListFragment
	with	Fragment

	/**
	 * Flag a Fragment as Creditor of an Activity
	 * 
	 * If a Fragment is flagged with this trait, its hosting Activity has to implement the Contract C.
	 * 
	 * @tparam C The Contract that is implemented by the Activity
	 */
	trait	Creditor[+C <: Contract]
	extends	content.Creditor[C]
	{
		this: Fragment =>

		override def debtor = getActivity.asInstanceOf[C]
	}
}