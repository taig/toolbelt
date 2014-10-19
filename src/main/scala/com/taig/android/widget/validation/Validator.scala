package com.taig.android.widget.validation

abstract class Validator( var enabled: Boolean, private var template: CharSequence )
{
	def message_( template: String ) = this.template = template

	def message = template

	def validate( value: CharSequence ) = !enabled || value.length() == 0
}

object Validator
{
	class Regex( enabled: Boolean, template: CharSequence, pattern: String ) extends Validator( enabled, template )
	{
		def this( message: CharSequence, expression: String ) = this( expression != null, message, expression )

		require( !enabled || pattern != null, "Pattern must be defined if Regex Validator is enabled" )

		override def validate( value: CharSequence ) =
		{
			super.validate( value ) || value.toString.matches( pattern )
		}
	}
}