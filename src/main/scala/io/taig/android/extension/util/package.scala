package io.taig.android.extension

import java.net.URLEncoder

import android.os.Bundle
import io.taig.android.util.Log
import io.taig.android.util.operation
import io.taig.android.util.Log.Tag

import scala.language.implicitConversions

package object util {
    implicit class ToolbeltBundle( bundle: Bundle ) extends operation.Bundle( bundle )

    implicit class ToolbeltString( string: String ) {
        def encode( charset: String = "UTF-8" ) = URLEncoder.encode( string, charset )
    }

    implicit def `String -> Log.Tag`( string: String ): Tag = Log.Tag( string )
}