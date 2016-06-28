package io.taig.android.intent.operation

import android.content.Context

final class intent( intent: android.content.Intent ) {
    /**
     * Check if there is an activity available to handle this intent
     *
     * @return <code>true</code> if this intent can be handled, <code>false</code> otherwise
     */
    def canBeHandled( implicit c: Context ) = intent.resolveActivity( c.getPackageManager ) != null
}