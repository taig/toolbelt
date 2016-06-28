package io.taig.android.extension

import java.net.URLEncoder

import android.os.Bundle
import io.taig.android.util.operation

import scala.language.implicitConversions

package object util {
    implicit class ToolbeltBundle( bundle: Bundle ) extends operation.Bundle( bundle )

    implicit class ToolbeltClass( `class`: Class[_] ) extends operation.Class( `class` )

    implicit class ToolbeltString( string: String ) {
        def encode( charset: String = "UTF-8" ) = URLEncoder.encode( string, charset )
    }
}