package io.taig.android.content.operation

import io.taig.android.content.Contextual

abstract class Intent( intent: android.content.Intent ) extends Contextual {
    /**
     * Check if there is an activity available to handle this intent
     *
     * @return <code>true</code> if this intent can be handled, <code>false</code> otherwise
     */
    def canBeHandled = intent.resolveActivity( context.getPackageManager ) != null
}