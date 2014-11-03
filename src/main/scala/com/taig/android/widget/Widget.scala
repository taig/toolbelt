package com.taig.android.widget

import android.content.res.TypedArray
import android.os.Parcelable
import android.util.AttributeSet
import android.view.View
import com.taig.android.content.Contextual

trait	Widget
extends	View
with	Contextual
{
	override implicit def context = getContext

	def attributes: AttributeSet

	def getStyledAttributes( attributes: Array[Int] ) = context.obtainStyledAttributes( this.attributes, attributes )

	protected def initialize( attributes: Array[Int], body: TypedArray => Unit )
	{
		val array = getStyledAttributes( attributes )
		body( array )
		array.recycle()
	}

	override def onSaveInstanceState() = super.onSaveInstanceState()

	override def onRestoreInstanceState( state: Parcelable ) = super.onRestoreInstanceState( state )
}

object Widget
{
	trait Styleable extends Widget
	{
		def style: Int

		def theme: Int

		override def getStyledAttributes( attributes: Array[Int] ) =
		{
			context.obtainStyledAttributes( this.attributes, attributes, style, theme )
		}
	}
}