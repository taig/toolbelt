package com.taig.android.content

import android.os.Bundle
import android.support.v4.app.{DialogFragment, FragmentActivity, ListFragment}
import android.view.{LayoutInflater, View, ViewGroup}
import com.taig.android.content

trait	Fragment
extends	android.support.v4.app.Fragment
with	Contextual
{
	// Manually store context, as it is mysteriously getting lost otherwise
	implicit override def context = Option( activity ).getOrElse( getActivity )

	private var activity: FragmentActivity = null

	override final def onCreate( state: Bundle )
	{
		super.onCreate( state )

		onCreate( Option( state ) )
	}

	def onCreate( state: Option[Bundle] ) {}

	override final def onActivityCreated( state: Bundle )
	{
		super.onActivityCreated( state )

		onActivityCreated( Option( state ) )
	}

	def onActivityCreated( state: Option[Bundle] ) {}

	override def onAttach( activity: android.app.Activity )
	{
		super.onAttach( activity )

		this.activity = activity match
		{
			case activity: FragmentActivity => activity
			case _ => null
		}
	}

	override final def onCreateView( inflater: LayoutInflater, container: ViewGroup, state: Bundle ) =
	{
		onCreateView( inflater, Option( container ), Option( state ) )
	}

	def onCreateView( inflater: LayoutInflater, container: Option[ViewGroup], state: Option[Bundle] ): View = null

	override final def onViewCreated( view: View, state: Bundle )
	{
		super.onViewCreated( view, state )

		onViewCreated( view, Option( state ) )
	}

	def onViewCreated( view: View, state: Option[Bundle] ) {}

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