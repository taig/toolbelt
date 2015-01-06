package com.taig.android.content

/**
 * Flag an Activity or Fragment as a Creditor of a [[Contract]]
 *
 * Creates a relationship between Activity and Fragment: if one entity implements the Creditor role, the other entity
 * should be implementing the Contract C.
 *
 * @tparam C The Contract type that is implemented by the debtor
 */
trait Creditor[+C]
{
	/**
	 * Get the debtor that implements the Contract C
	 *
	 * @return Debtor that implements Contract C
	 */
	def debtor: C
}