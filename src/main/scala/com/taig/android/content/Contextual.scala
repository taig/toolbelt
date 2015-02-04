package com.taig.android.content

import android.content.Context

import scala.reflect.ClassTag

trait Contextual
{
	implicit def context: Context

	def startActivity[A: ClassTag]: Unit = context.startActivity( Intent[A] )
}