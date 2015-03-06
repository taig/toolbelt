package com.taig.android.widget.validation

abstract class Validator( var enabled: Boolean, private var template: CharSequence )
{
	def message_( template: String ) = this.template = template

	def message = template

	def validate( value: CharSequence ) = !enabled || transform( value ).length() == 0

	/**
	 * An additional String transformation before validation
	 * 
	 * E.g. a useful utility for phone numbers to remove whitespace before validation and content retrieval.
	 * 
	 * @param value Raw content String to transform
	 * @return Transformed value, default implementation is the identity function
	 */
	def transform( value: CharSequence ): CharSequence = value
}

object Validator
{
	class Regex( enabled: Boolean, template: CharSequence, pattern: String ) extends Validator( enabled, template )
	{
		def this( message: CharSequence, expression: String ) = this( expression != null, message, expression )

		require( !enabled || pattern != null, "Pattern must be defined if Regex Validator is enabled" )

		override def validate( value: CharSequence ) =
		{
			super.validate( value ) || transform( value ).toString.matches( pattern )
		}
	}
}