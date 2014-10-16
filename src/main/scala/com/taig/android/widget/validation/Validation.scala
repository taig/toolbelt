package com.taig.android.widget.validation

abstract class Validation( var enabled: Boolean, var message: CharSequence )
{
	def validate( value: CharSequence ): Boolean
}

object Validation
{
	class Regex( enabled: Boolean, message: CharSequence, expression: String ) extends Validation( enabled, message )
	{
		override def validate( value: CharSequence ) = !enabled || enabled && value.toString.matches( expression )
	}
}