package com.taig.android.content

/**
 * Flag an Activity or Fragment as a Creditor of a [[Contract]]
 *
 * Creates a relationship between Activity and Fragment: if one entity implements the Creditor role, the other entity
 * should be implementing the Contract C.
 *
 * @tparam C The Contract that is implemented by the debtor(s)
 */
trait Creditor[C]
{
	def debtor: C
}