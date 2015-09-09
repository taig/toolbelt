package io.taig.android.widget.ops

import android.view.ViewPropertyAnimator

trait Animation {
    def view: android.view.View

    def popIn(): ViewPropertyAnimator = view.animate().scaleX( 1 ).scaleY( 1 ).setDuration( 150 )

    def popOut(): ViewPropertyAnimator = view.animate().scaleX( 0 ).scaleY( 0 ).setDuration( 150 )
}