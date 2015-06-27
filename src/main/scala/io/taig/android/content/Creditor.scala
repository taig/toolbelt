package io.taig.android.content

import android.os.Bundle

/**
 * A Fragment may be a Creditor, forcing the hosting Activity to implement its Contract
 */
trait	Creditor[+C <: Contract]
extends	android.app.Fragment
{
	def debtor: C = getActivity.asInstanceOf[C]

	override def onActivityCreated( state: Bundle ) =
	{
		super.onActivityCreated( state )

		require( getActivity.isInstanceOf[C], getActivity.getClass.getName + " has to implement the Contract" )
	}
}