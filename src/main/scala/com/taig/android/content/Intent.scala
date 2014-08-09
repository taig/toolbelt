package com.taig.android.content

import android.content.Context
import android.net.Uri
import android.{ content => android }

import scala.reflect.ClassTag

object Intent
{
	def apply( intent: android.Intent ): android.Intent = new android.Intent( intent )
	
	def apply( action: String ): android.Intent = new android.Intent( action )

	def apply( action: String, uri: Uri ): android.Intent = new android.Intent( action, uri )

	def apply( `class`: Class[_] )( implicit context: Context ): android.Intent = new android.Intent( context, `class` )

	def apply[T]( implicit context: Context, tag: ClassTag[T] ): android.Intent =
	{
		new android.Intent( context, implicitly[ClassTag[T]].runtimeClass )
	}

	def apply( action: String, uri: Uri, `class`: Class[_] )( implicit context: Context ): android.Intent =
	{
		new android.Intent( action, uri, context, `class` )
	}
}