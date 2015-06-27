package io.taig.android.content.internal

import android.os.Bundle

import scala.reflect._

/**
 * A Fragment may be a Creditor, forcing the hosting Activity to implement its Contract
 */
abstract class	Creditor[+C: ClassTag]
extends			android.app.Fragment
{
	def debtor: C = getActivity.asInstanceOf[C]

	override def onActivityCreated( state: Bundle ) =
	{
		super.onActivityCreated( state )

		require(
			classTag[C].runtimeClass.isInstance( getActivity ),
			getActivity.getClass.getName + " has to implement the Contract"
		)
	}
}