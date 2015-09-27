package io.taig.android.extension

import java.net.URLEncoder

import io.taig.android.util.Log
import io.taig.android.util.Log.Tag

import scala.language.implicitConversions

package object util {
    implicit class ToolbeltString( string: String ) {
        def encode( charset: String = "UTF-8" ) = URLEncoder.encode( string, charset )
    }

    implicit def `String -> Log.Tag`( string: String ): Tag = Log.Tag( string )
}