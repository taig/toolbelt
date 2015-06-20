package io.taig.android.util

import android.{ util => android }

object Log
{
	def d( message: Any )( implicit tag: Tag ) = android.Log.d( tag.name, message.toString )

	def d( message: Any, error: Throwable )( implicit tag: Tag ) = android.Log.d( tag.name, message.toString, error )

	def e( message: Any )( implicit tag: Tag ) = android.Log.e( tag.name, message.toString )

	def e( message: Any, error: Throwable )( implicit tag: Tag ) = android.Log.e( tag.name, message.toString, error )

	def i( message: Any )( implicit tag: Tag ) = android.Log.i( tag.name, message.toString )

	def i( message: Any, error: Throwable )( implicit tag: Tag ) = android.Log.i( tag.name, message.toString, error )

	def v( message: Any )( implicit tag: Tag ) = android.Log.v( tag.name, message.toString )

	def v( message: Any, error: Throwable )( implicit tag: Tag ) = android.Log.v( tag.name, message.toString, error )

	def w( message: Any )( implicit tag: Tag ) = android.Log.w( tag.name, message.toString )

	def w( message: Any, error: Throwable )( implicit tag: Tag ) = android.Log.w( tag.name, message.toString, error )

	def wtf( message: Any )( implicit tag: Tag ) = android.Log.wtf( tag.name, message.toString )

	def wtf( message: Any, error: Throwable )( implicit tag: Tag ) = android.Log.wtf( tag.name, message.toString, error )

	case class	Tag( name: String )
	extends		AnyVal
}