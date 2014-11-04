package com.taig.android.widget

import android.content.Context
import android.util.AttributeSet

class	Spinner( val attributes: AttributeSet = null, val style: Int = android.R.attr.editTextStyle, val mode: Int = -1 )( implicit context: Context )
extends	android.widget.Spinner( context, attributes, style, mode )
with	Widget.Styleable
{
	def this( context: Context, attributes: AttributeSet, style: Int, mode: Int ) = this( attributes, style, mode = mode )( context )

	def this( context: Context, attributes: AttributeSet, style: Int ) = this( attributes, style )( context )

	def this( context: Context, attributes: AttributeSet ) = this( attributes )( context )

	def this( context: Context, mode: Int ) = this( mode = mode )( context )

	def this( context: Context ) = this()( context )
}