package io.taig.android.content.ops

import io.taig.android.content.Contextual

trait Intent
        extends Contextual {
    def intent: android.content.Intent

    /**
     * Check if there is an activity available to handle this intent
     *
     * @return <code>true</code> if this intent can be handled, <code>false</code> otherwise
     */
    def canBeHandled = intent.resolveActivity( context.getPackageManager ) != null
}