package com.taig.android.widget

import android.content.Context
import android.util.AttributeSet
import android.widget.TextView

class	Text( val context: Context, val attributes: AttributeSet, val style: Int )
extends	TextView( context, attributes, style )
with	Widget
{
	def this( context: Context, attributes: AttributeSet ) = this( context, attributes, android.R.attr.textViewStyle )

	def this( context: Context ) = this( context, null )
}

object Text
{
	object Listener
	{
		trait OnEditorDone
		{
			def onEditorDone( view: android.widget.EditText ): Boolean
		}
	}
}