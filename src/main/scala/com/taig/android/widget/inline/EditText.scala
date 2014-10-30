package com.taig.android.widget.inline

import android.content.Context
import android.util.AttributeSet
import com.taig.android.widget
import com.taig.android.widget.Inline

class	EditText( context: Context, attributes: AttributeSet )
extends
{
	protected val input = new widget.EditText( context, attributes )
}
with	Inline( context, attributes )
{
	def this( context: Context ) = this( context, null )

	def getText = input.getText
}