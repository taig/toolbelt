package com.taig.android.widget

import android.content.Context
import android.util.AttributeSet
import android.widget.TextView

class	Text( val attributes: AttributeSet = null, val style: Int = android.R.attr.textViewStyle, val theme: Int = 0 )( implicit context: Context )
extends	TextView( context, attributes, style, theme )
with	Widget.Styleable
{
	def this( context: Context, attributes: AttributeSet, style: Int, theme: Int ) = this( attributes, style )( context )

	def this( context: Context, attributes: AttributeSet, style: Int ) = this( attributes, style )( context )

	def this( context: Context, attributes: AttributeSet ) = this( attributes )( context )

	def this( context: Context ) = this()( context )
}