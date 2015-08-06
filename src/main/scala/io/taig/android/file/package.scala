package io.taig.android

import java.io.{ ByteArrayOutputStream, File }

import android.graphics.Bitmap
import android.util.Base64

package object file {
    implicit class RichBitmap( bitmap: Bitmap ) {
        def toBase64(): String = {
            val stream = new ByteArrayOutputStream()

            try {
                bitmap.compress( Bitmap.CompressFormat.PNG, 100, stream )
                Base64.encodeToString( stream.toByteArray, Base64.DEFAULT )
            }
            finally {
                stream.close()
            }
        }
    }

    implicit class RichFile( file: File ) {
        def deleteRecursively() {
            def deleteRecursively( file: File ): Unit = {
                if ( file.isDirectory ) {
                    file.listFiles().foreach( deleteRecursively )
                }
                else {
                    file.delete()
                }
            }

            deleteRecursively( file )
        }
    }
}