package io.taig.android.util.operation

import java.io.ByteArrayOutputStream

import android.graphics.Bitmap
import android.util.Base64

final class bitmap( bitmap: Bitmap ) {
    def toBase64: String = {
        val stream = new ByteArrayOutputStream()

        try {
            bitmap.compress( Bitmap.CompressFormat.PNG, 100, stream )
            Base64.encodeToString( stream.toByteArray, Base64.DEFAULT )
        } finally {
            stream.close()
        }
    }
}