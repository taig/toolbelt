package com.taig.android.widget

import android.content.Context
import android.widget.Toast.LENGTH_LONG
import android.{widget => android}

object Toast
{
	def apply( message: CharSequence, duration: Int )( implicit context: Context ): android.Toast =
	{
		android.Toast.makeText( context, message, duration )
	}
	
	def apply( message: CharSequence )( implicit context: Context ): android.Toast = apply( message, LENGTH_LONG )

	def apply( message: Int, duration: Int )( implicit context: Context ): android.Toast =
	{
		android.Toast.makeText( context, message, duration )
	}

	def apply( message: Int )( implicit context: Context ): android.Toast = apply( message, LENGTH_LONG )
}