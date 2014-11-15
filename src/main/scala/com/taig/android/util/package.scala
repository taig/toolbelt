package com.taig.android

package object util
{
	implicit class RichBoolean( boolean: Boolean )
	{
		def asOption[A]( a: => A ): Option[A] = if( boolean ) Option( a ) else None
	}
}