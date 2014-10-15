package com.taig.android.widget

trait Validatable
{
	/**
	 * Check if the widget succeeds its validation constraints
	 * 
	 * @return <code>true</code> true if all constrains are successfully passed or there are none, <code>false</code>
	 *         otherwise
	 */
	def isValid: Boolean

	/**
	 * Check if the widget passes its validation rules and adjust the widget layout to indicate the current state
	 * 
	 * @return <code>true</code> true if all constrains are successfully passed or there are none, <code>false</code>
	 *         otherwise
	 */
	def validate(): Boolean
}

object Validatable
{
	val Flag = new
	{
		val Alpha = 1

		val AlphaDash = 2

		val AlphaNumeric = 3

		val Email = 4

		val Integer = 5

		val Numeric = 6

		val Phone = 7
	}
}