package com.taig.android.widget

import android.content.Context
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG

object Toast
{
	def apply( message: CharSequence, duration: Int )( implicit context: Context ): Toast =
	{
		android.widget.Toast.makeText( context, message, duration )
	}
	
	def apply( message: CharSequence )( implicit context: Context ): Toast = apply( message, LENGTH_LONG )

	def apply( message: Int, duration: Int )( implicit context: Context ): Toast =
	{
		android.widget.Toast.makeText( context, message, duration )
	}

	def apply( message: Int )( implicit context: Context ): Toast = apply( message, LENGTH_LONG )
}