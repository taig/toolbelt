package com.taig.android

import java.net.URLEncoder

package object util
{
	implicit class RichBoolean( boolean: Boolean )
	{
		def asOption[A]( a: => A ): Option[A] = if( boolean ) Option( a ) else None
	}

	implicit class RichString( string: String )
	{
		def encode( charset: String = "UTF-8" ) = URLEncoder.encode( string, charset )
	}
}