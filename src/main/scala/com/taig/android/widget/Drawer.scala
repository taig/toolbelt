package com.taig.android.widget

import android.content.Context
import android.support.v4.widget.DrawerLayout
import android.util.AttributeSet
import android.view.View.MeasureSpec

class	Drawer( val attributes: AttributeSet = null, val style: Int = 0 )( implicit context: Context )
extends	DrawerLayout( context, attributes, style )
with	Widget.Styleable
{
	def this( context: Context, attributes: AttributeSet, style: Int ) = this( attributes, style )( context )

	def this( context: Context, attributes: AttributeSet ) = this( attributes )( context )

	def this( context: Context ) = this()( context )

	override def onMeasure( widthMeasureSpec: Int, heightMeasureSpec: Int )
	{
		super.onMeasure(
			MeasureSpec.makeMeasureSpec( MeasureSpec.getSize( widthMeasureSpec ), MeasureSpec.EXACTLY ),
			MeasureSpec.makeMeasureSpec( MeasureSpec.getSize( heightMeasureSpec ), MeasureSpec.EXACTLY )
		)
	}
}