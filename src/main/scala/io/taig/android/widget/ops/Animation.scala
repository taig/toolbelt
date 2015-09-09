package io.taig.android.widget.ops

import android.view.ViewPropertyAnimator

abstract class Animation( animation: ViewPropertyAnimator ) {
    def popIn(): ViewPropertyAnimator = {
        animation
            .setDuration( 150 )
            .rotation( 0 )
            .scaleX( 1 )
            .scaleY( 1 )
    }

    def popOut(): ViewPropertyAnimator = {
        animation
            .setDuration( 150 )
            .rotation( -35 )
            .scaleX( 0 )
            .scaleY( 0 )
    }
}