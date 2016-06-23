package io.taig.android.content

import android.content.Context
import android.net.Uri
import android.{ content ⇒ android }

import scala.reflect.ClassTag

object Intent {
    def apply(): android.Intent = new android.Intent()

    def apply( intent: android.Intent ): android.Intent = new android.Intent( intent )

    def apply( action: String ): android.Intent = new android.Intent( action )

    def apply( action: String, uri: Uri ): android.Intent = new android.Intent( action, uri )

    def apply( `class`: Class[_] )( implicit c: Context ): android.Intent = new android.Intent( c, `class` )

    def apply[T]( implicit c: Context, tag: ClassTag[T] ): android.Intent = {
        new android.Intent( c, implicitly[ClassTag[T]].runtimeClass )
    }

    def apply( action: String, uri: Uri, `class`: Class[_] )( implicit c: Context ): android.Intent = {
        new android.Intent( action, uri, c, `class` )
    }
}