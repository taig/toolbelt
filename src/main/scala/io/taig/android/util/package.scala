package io.taig.android

import java.net.URLEncoder

import io.taig.android.util.Log.Tag

import scala.language.implicitConversions

package object util {
    implicit class RichBoolean( boolean: Boolean ) {
        def asOption[A]( a: ⇒ A ): Option[A] = if ( boolean ) Option( a ) else None
    }

    implicit class RichString( string: String ) {
        def encode( charset: String = "UTF-8" ) = URLEncoder.encode( string, charset )
    }

    implicit def `String -> Log.Tag`( string: String ): Tag = Log.Tag( string )
}