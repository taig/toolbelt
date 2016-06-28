package io.taig.android

import android.util.Log._

import scala.reflect._

package object log {
    type Log = android.util.Log

    object Log {
        def d( message: Any )( implicit t: Tag ) = print( message, android.util.Log.d( t.name, _ ) )

        def d( message: Any, error: Throwable )( implicit t: Tag ) = {
            print( message, error, android.util.Log.d( t.name, _ ) )
        }

        def e( message: Any )( implicit t: Tag ) = print( message, android.util.Log.e( t.name, _ ) )

        def e( message: Any, error: Throwable )( implicit t: Tag ) = {
            print( message, error, android.util.Log.e( t.name, _ ) )
        }

        def i( message: Any )( implicit t: Tag ) = print( message, android.util.Log.i( t.name, _ ) )

        def i( message: Any, error: Throwable )( implicit t: Tag ) = {
            print( message, error, android.util.Log.i( t.name, _ ) )
        }

        def v( message: Any )( implicit t: Tag ) = print( message, android.util.Log.v( t.name, _ ) )

        def v( message: Any, error: Throwable )( implicit t: Tag ) = {
            print( message, error, android.util.Log.v( t.name, _ ) )
        }

        def w( message: Any )( implicit t: Tag ) = print( message, android.util.Log.w( t.name, _ ) )

        def w( message: Any, error: Throwable )( implicit t: Tag ) = {
            print( message, error, android.util.Log.w( t.name, _ ) )
        }

        def wtf( message: Any ) = print( message, android.util.Log.wtf( "WTF", _ ) )

        def wtf( message: Any, error: Throwable ) = {
            print( message, android.util.Log.wtf( "WTF", _, error ) )
        }

        private def print( message: Any, log: String ⇒ Unit ) = message.toString.grouped( 1000 ).foreach( log )

        private def print( message: Any, throwable: Throwable, log: String ⇒ Unit ) = {
            ( message + "\n" + getStackTraceString( throwable ) ).grouped( 1000 ).foreach( log )
        }

        case class Tag( name: String ) extends AnyVal

        object Tag {
            def apply[T: ClassTag]: Tag = Tag( classTag[T].runtimeClass.getCanonicalName )
        }
    }
}