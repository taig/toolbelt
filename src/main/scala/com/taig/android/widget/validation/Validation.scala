package com.taig.android.widget.validation

abstract class Validation( var enabled: Boolean, var message: CharSequence )
{
	def validate( value: CharSequence ) = !enabled || value.length() == 0
}

object Validation
{
	class Regex( enabled: Boolean, message: CharSequence, expression: String ) extends Validation( enabled, message )
	{
		override def validate( value: CharSequence ) =
		{
			super.validate( value ) || value.toString.matches( expression )
		}
	}
}