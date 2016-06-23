package io.taig.android.widget

import android.content.Context
import android.widget.Toast.LENGTH_LONG
import android.{ widget ⇒ android }

object Toast {
    @inline
    def apply( message: CharSequence, duration: Int )( implicit c: Context ): android.Toast = {
        android.Toast.makeText( c, message, duration )
    }

    @inline
    def apply( message: CharSequence )( implicit c: Context ): android.Toast = apply( message, LENGTH_LONG )

    @inline
    def apply( message: Int, duration: Int )( implicit c: Context ): android.Toast = {
        android.Toast.makeText( c, message, duration )
    }

    @inline
    def apply( message: Int )( implicit c: Context ): android.Toast = apply( message, LENGTH_LONG )
}