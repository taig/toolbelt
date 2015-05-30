package io.taig.android.widget.validation

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