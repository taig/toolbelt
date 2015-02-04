package com.taig.android.content

import android.content.Context

trait Contextual
{
	implicit def context: Context

	def startActivity[A]: Unit = context.startActivity( Intent[A] )
}