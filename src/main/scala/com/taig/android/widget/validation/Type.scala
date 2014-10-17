package com.taig.android.widget.validation

import android.util.{Log, Patterns}

object Type
{
	class Alpha( enabled: Boolean, message: CharSequence ) extends Validation.Regex( enabled, message, "[\\p{L}]*" )

	class AlphaDash( enabled: Boolean, message: CharSequence ) extends Validation.Regex( enabled, message, "[\\p{L}\\-]*" )

	class AlphaNumeric( enabled: Boolean, message: CharSequence ) extends Validation.Regex( enabled, message, "[\\p{L}0-9]*" )

	class Email( enabled: Boolean, message: CharSequence ) extends Validation.Regex( enabled, message, Patterns.EMAIL_ADDRESS.pattern() )

	class Integer( enabled: Boolean, message: CharSequence ) extends Validation.Regex( enabled, message, "[0-9]*" )

	class Length( enabled: Boolean, message: CharSequence, var value: Int ) extends Validation( enabled, message )
	{
		override def validate( value: CharSequence ) = super.validate( value ) || value.length() == this.value
	}

	abstract class Match( enabled: Boolean, message: CharSequence, var target: Int ) extends Validation( enabled, message )
	{
		def this( message: CharSequence, target: Int ) = this( target > 0, message, target )

		override def validate( value: CharSequence ) =
		{
			Log.d( "ASDF", s"Compare: '$value' == '$find'" )
			Log.d( "ASDF", s"${value.getClass.getName} vs ${find.getClass.getName}" )
			super.validate( value ) || value == find
		}

		protected def find: CharSequence
	}

	class Max( enabled: Boolean, message: CharSequence, var length: Int ) extends Validation( enabled, message )
	{
		override def validate( value: CharSequence ) = super.validate( value ) || value.length() <= length
	}

	class Min( enabled: Boolean, message: CharSequence, var length: Int ) extends Validation( enabled, message )
	{
		override def validate( value: CharSequence ) = super.validate( value ) || value.length() >= length
	}

	class Numeric( enabled: Boolean, message: CharSequence ) extends Validation.Regex( enabled, message, "\\d*([\\.,]\\d+)?" )

	class Phone( enabled: Boolean, message: CharSequence ) extends Validation.Regex( enabled, message, "(\\+?\\d+)?" )

	class Regex( enabled: Boolean, message: CharSequence, var expression: String ) extends Validation.Regex( enabled, message, expression )

	class Required( enabled: Boolean, message: CharSequence ) extends Validation( enabled, message )
	{
		override def validate( value: CharSequence ) = !enabled || value.length() > 0
	}
}