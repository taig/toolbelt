package com.taig.android.widget.validation

import android.util.Patterns

object Type
{
	class Alpha( enabled: Boolean, message: CharSequence ) extends Validator.Regex( enabled, message, "[\\p{L}]*" )

	class AlphaDash( enabled: Boolean, message: CharSequence ) extends Validator.Regex( enabled, message, "[\\p{L}\\-]*" )

	class AlphaNumeric( enabled: Boolean, message: CharSequence ) extends Validator.Regex( enabled, message, "[\\p{L}0-9]*" )

	class Email( enabled: Boolean, message: CharSequence ) extends Validator.Regex( enabled, message, Patterns.EMAIL_ADDRESS.pattern() )

	class Integer( enabled: Boolean, message: CharSequence ) extends Validator.Regex( enabled, message, "[0-9]*" )

	class Length( enabled: Boolean, message: CharSequence, var value: Int ) extends Validator( enabled, message )
	{
		def this( message: CharSequence, value: Int ) = this( value != -1, message, value )

		override def getMessage = String.format( message.toString, value )

		override def validate( value: CharSequence ) = super.validate( value ) || value.length() == this.value
	}

	abstract class Matches( enabled: Boolean, message: CharSequence, var target: Int ) extends Validator( enabled, message )
	{
		def this( message: CharSequence, target: Int ) = this( target > 0, message, target )

		override def validate( value: CharSequence ) = super.validate( value ) || value.toString == find.toString

		protected def find: CharSequence
	}

	class Max( enabled: Boolean, message: CharSequence, var length: Int ) extends Validator( enabled, message )
	{
		def this( message: CharSequence, length: Int ) = this( length < Int.MaxValue, message, length )

		override def getMessage = String.format( message.toString, length )

		override def validate( value: CharSequence ) = super.validate( value ) || value.length() <= length
	}

	class Min( enabled: Boolean, message: CharSequence, var length: Int ) extends Validator( enabled, message )
	{
		def this( message: CharSequence, length: Int ) = this( length > Int.MinValue, message, length )

		override def getMessage = String.format( message.toString, length )

		override def validate( value: CharSequence ) = super.validate( value ) || value.length() >= length
	}

	class Numeric( enabled: Boolean, message: CharSequence ) extends Validator.Regex( enabled, message, "\\d*([\\.,]\\d+)?" )

	class Phone( enabled: Boolean, message: CharSequence ) extends Validator.Regex( enabled, message, "(\\+?\\d+)?" )

	class Required( enabled: Boolean, message: CharSequence ) extends Validator( enabled, message )
	{
		override def validate( value: CharSequence ) = !enabled || value.length() > 0
	}
}