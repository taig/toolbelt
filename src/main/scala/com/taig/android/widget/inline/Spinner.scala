package com.taig.android.widget.inline

import android.content.Context
import android.util.AttributeSet
import com.taig.android.widget.{Widget, Inline}

class	Spinner( c: Context, attributes: AttributeSet )
extends
{
	protected val input = new android.widget.Spinner( c, attributes ) with Widget
	{
		override implicit val context = c
	}
}
with	Inline( c, attributes )
{
	def this( context: Context ) = this( context, null )

	def getText = input.getSelectedItem.toString
}