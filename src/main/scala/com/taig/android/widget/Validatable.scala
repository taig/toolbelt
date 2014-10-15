package com.taig.android.widget

trait Validatable
{
	def isValid: Boolean

	def validate(): Unit
}

object Validatable
{
	val Flag = new
	{
		val Alpha = 1 << 0

		val AlphaDash = 1 << 1

		val AlphaNumeric = 1 << 2

		val Email = 1 << 3

		val Integer = 1 << 4

		val Numeric = 1 << 5

		val Phone = 1 << 6

		val Required = 1 << 7

		val None = 1 << 8
	}
}