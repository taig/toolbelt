package io.taig.android

import scala.reflect._

package object util {
    /**
     * Generate an identifier that can be used as request_code for startActivityForResult
     * 
     * The generate identifier must be > 0 an <= 65535.
     */
    def identify[T: ClassTag]: Int = {
        val hash = classTag[T].runtimeClass.getName.hashCode
        val a = hash + 1 + ( ( hash >> 63 ) << 1 )
        val b = a - ( a >> 63 )
        b & 65535
    }
}