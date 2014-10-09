package com.taig.android.content

/**
 * The entity that expects his partner to fulfil a [[Contract]]
 */
trait Creditor[C <: Contract]
{
	def contract: C
}