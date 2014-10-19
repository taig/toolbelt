package com.taig.android.widget.validation

import android.util.Patterns

object Type
{
	class Alpha( enabled: Boolean, template: CharSequence ) extends Validator.Regex( enabled, template, "[\\p{L}]*" )

	class AlphaDash( enabled: Boolean, template: CharSequence ) extends Validator.Regex( enabled, template, "[\\p{L}\\-]*" )

	class AlphaNumeric( enabled: Boolean, template: CharSequence ) extends Validator.Regex( enabled, template, "[\\p{L}0-9]*" )

	class Email( enabled: Boolean, template: CharSequence ) extends Validator.Regex( enabled, template, Patterns.EMAIL_ADDRESS.pattern() )

	class Integer( enabled: Boolean, template: CharSequence ) extends Validator.Regex( enabled, template, "[0-9]*" )

	class Length( enabled: Boolean, template: CharSequence, var value: Int ) extends Validator( enabled, template )
	{
		def this( template: CharSequence, value: Int ) = this( value != -1, template, value )

//		override def message = template.toString.format( value )

		override def validate( value: CharSequence ) = super.validate( value ) || value.length() == this.value
	}

	abstract class Matches( enabled: Boolean, template: CharSequence, var target: Int ) extends Validator( enabled, template )
	{
		def this( template: CharSequence, target: Int ) = this( target > 0, template, target )

		override def validate( value: CharSequence ) = super.validate( value ) || value.toString == find.toString

		protected def find: CharSequence
	}

	class Max( enabled: Boolean, template: CharSequence, var length: Int ) extends Validator( enabled, template )
	{
		def this( template: CharSequence, length: Int ) = this( length < Int.MaxValue, template, length )

//		override def message = template.toString.format( length )

		override def validate( value: CharSequence ) = super.validate( value ) || value.length() <= length
	}

	class Min( enabled: Boolean, template: CharSequence, var length: Int ) extends Validator( enabled, template )
	{
		def this( template: CharSequence, length: Int ) = this( length > Int.MinValue, template, length )

//		override def message = template.toString.format( length )

		override def validate( value: CharSequence ) = super.validate( value ) || value.length() >= length
	}

	class Numeric( enabled: Boolean, template: CharSequence ) extends Validator.Regex( enabled, template, "\\d*([\\.,]\\d+)?" )

	class Phone( enabled: Boolean, template: CharSequence ) extends Validator.Regex( enabled, template, "(\\+?\\d+)?" )

	class Required( enabled: Boolean, template: CharSequence ) extends Validator( enabled, template )
	{
		override def validate( value: CharSequence ) = !enabled || value.length() > 0
	}
}