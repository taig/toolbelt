package com.taig.android.widget.inline

import android.content.Context
import android.util.AttributeSet
import com.taig.android.widget.{Widget, Inline}

class	Spinner( context: Context, attributes: AttributeSet )
extends	Inline( context, attributes )
{
	def this( context: Context ) = this( context, null )

	override protected val input = new android.widget.Spinner( context, attributes ) with Widget
	{
		override implicit val context = Spinner.this.context
	}

	def getText = input.getSelectedItem.toString
}