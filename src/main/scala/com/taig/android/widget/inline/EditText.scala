package com.taig.android.widget.inline

import android.content.Context
import android.util.AttributeSet
import com.taig.android.widget
import com.taig.android.widget.Inline

class	EditText( context: Context, attributes: AttributeSet )
extends	Inline( context, attributes )
{
	def this( context: Context ) = this( context, null )

	override protected val input = new widget.EditText( context, attributes )

	def getText = input.getText
}