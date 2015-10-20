package io.taig.android.util.operation

import io.taig.android.extension.util._

abstract class Bundle( bundle: android.os.Bundle ) {
    def toMap: Map[String, Any] = {
        import scala.collection.JavaConversions._

        bundle.keySet()
            .map( key ⇒ key → bundle.get( key ) )
            .map {
                case ( key, bundle: android.os.Bundle ) ⇒ key → bundle.toMap
                case ( key, value )                     ⇒ key → value
            }
            .toMap
    }
}