package io.taig.android.util.operation

import java.net.URLEncoder

final class string( string: String ) {
    def encode( charset: String = "UTF-8" ): String = URLEncoder.encode( string, charset )
}