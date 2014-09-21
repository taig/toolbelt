package com.taig.android

package object content
{
	implicit class RichContext( value: android.content.Context ) extends Context
	{
		override protected[content] implicit val context = value
	}

	implicit def `Context -> Android.Context`( context: Context ) = context.context
}