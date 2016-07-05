package io.taig.android.util.operation

import android.os.Bundle
import io.taig.android.util.syntax.bundle._

final class bundle( bundle: Bundle ) {
    def toMap: Map[String, Any] = {
        import scala.collection.JavaConversions._

        bundle.keySet()
            .map( key ⇒ key → bundle.get( key ) )
            .map {
                case ( key, bundle: Bundle ) ⇒ key → bundle.toMap
                case ( key, value )          ⇒ key → value
            }
            .toMap
    }
}