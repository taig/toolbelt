package com.taig.android.util

trait Companion
{
	implicit lazy val Tag = new Log.Tag( getClass.getName.reverse.dropWhile( _ == '$' ).reverse )
}