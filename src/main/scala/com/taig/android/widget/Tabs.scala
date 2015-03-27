package com.taig.android.widget

import android.content.Context
import android.util.AttributeSet
import com.astuetz.PagerSlidingTabStrip

class	Tabs( val attributes: AttributeSet = null, val style: Int = 0 )( implicit context: Context )
extends	PagerSlidingTabStrip( context, attributes, style )
with	Widget.Styleable
{
	def this( context: Context, attributes: AttributeSet, style: Int ) = this( attributes, style )( context )

	def this( context: Context, attributes: AttributeSet ) = this( attributes )( context )

	def this( context: Context ) = this()( context )
}