package io.taig.android.content

/**
 * A Fragment may be a Creditor, forcing the hosting Activity to implement its contract
 */
trait	Creditor[+C]
extends	Fragment
{
	// It would be wise to check if the Activity implements C in onActivityCreated(), but that would enforce us to
	// implement an implicit classTag field in the children. This might work out in the future with trait parameters.
	// Until then, please implement the fucking contract.

	def debtor: C = context.asInstanceOf[C]
}