package io.taig.android.content

import android.os.Bundle

/**
 * A Fragment may be a Creditor, forcing the hosting Activity to implement its Contract
 */
trait	Creditor[+C]
extends	Fragment
{
	def debtor: C = getActivity.asInstanceOf[C]

	override def onActivityCreated( state: Option[Bundle] ) =
	{
		super.onActivityCreated( state )

		// It is okay to do the ugly cast try here, because there is nothing to gain with a ClassTag. It would enforce
		// us to implement a field in the child without providing any actual benefit. This is intended as debugging
		// message during the development process.
		try
		{
			debtor
		}
		catch
		{
			case _: ClassCastException =>
			{
				sys.error( getActivity.getClass.getName + " has to implement the Contract" )
			}
		}
	}
}