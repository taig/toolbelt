package com.taig.android.widget.validation

abstract class Validator( var enabled: Boolean, var message: CharSequence )
{
	def validate( value: CharSequence ) = !enabled || value.length() == 0
}

object Validator
{
	class Regex( enabled: Boolean, message: CharSequence, pattern: String ) extends Validator( enabled, message )
	{
		def this( message: CharSequence, expression: String ) = this( expression != null, message, expression )

		require( !enabled || pattern != null, "Pattern must be defined if this Validator is enabled" )

		override def validate( value: CharSequence ) =
		{
			super.validate( value ) || value.toString.matches( pattern )
		}
	}
}