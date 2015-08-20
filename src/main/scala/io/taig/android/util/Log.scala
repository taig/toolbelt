package io.taig.android.util

import android.util.Log.getStackTraceString
import android.{ util ⇒ android }

object Log {
    def d( message: Any )( implicit tag: Tag ) = print( message, android.Log.d( tag.name, _ ) )

    def d( message: Any, error: Throwable )( implicit tag: Tag ) = print( message, error, android.Log.d( tag.name, _ ) )

    def e( message: Any )( implicit tag: Tag ) = print( message, android.Log.e( tag.name, _ ) )

    def e( message: Any, error: Throwable )( implicit tag: Tag ) = print( message, error, android.Log.e( tag.name, _ ) )

    def i( message: Any )( implicit tag: Tag ) = print( message, android.Log.i( tag.name, _ ) )

    def i( message: Any, error: Throwable )( implicit tag: Tag ) = print( message, error, android.Log.i( tag.name, _ ) )

    def v( message: Any )( implicit tag: Tag ) = print( message, android.Log.v( tag.name, _ ) )

    def v( message: Any, error: Throwable )( implicit tag: Tag ) = print( message, error, android.Log.v( tag.name, _ ) )

    def w( message: Any )( implicit tag: Tag ) = print( message, android.Log.w( tag.name, _ ) )

    def w( message: Any, error: Throwable )( implicit tag: Tag ) = print( message, error, android.Log.w( tag.name, _ ) )

    def wtf( message: Any )( implicit tag: Tag ) = print( message, android.Log.wtf( tag.name, _ ) )

    def wtf( message: Any, error: Throwable )( implicit tag: Tag ) = {
        print( message, android.Log.wtf( tag.name, _, error ) )
    }

    private def print( message: Any, log: String ⇒ Unit ) = message.toString.grouped( 1000 ).foreach( log )

    private def print( message: Any, throwable: Throwable, log: String ⇒ Unit ) = {
        ( message + "\n" + getStackTraceString( throwable ) ).grouped( 1000 ).foreach( log )
    }

    case class Tag( name: String )
        extends AnyVal
}