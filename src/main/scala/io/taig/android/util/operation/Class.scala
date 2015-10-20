package io.taig.android.util.operation

import io.taig.android.extension.util.ToolbeltClass

abstract class Class( `class`: java.lang.Class[_] ) {
    /**
     * Discover all involved classes and traits
     */
    def parents(): Set[java.lang.Class[_]] = {
        val interfaces = `class`.getInterfaces

        val parents = `class`.getSuperclass match {
            case null   ⇒ Set.empty
            case parent ⇒ Set( parent ) ++ parent.parents()
        }

        ( interfaces ++ parents ).toSet
    }
}