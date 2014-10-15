package com.taig.android.widget

trait Validatable
{
	def isValid: Boolean

	def validate(): Unit
}