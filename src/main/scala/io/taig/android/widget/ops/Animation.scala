package io.taig.android.widget.ops

import android.support.v4.view.ViewPropertyAnimatorCompat

abstract class Animation( animation: ViewPropertyAnimatorCompat ) {
    def popIn(): ViewPropertyAnimatorCompat = {
        animation
            .setDuration( 150 )
            .rotation( 0 )
            .scaleX( 1 )
            .scaleY( 1 )
    }

    def popOut(): ViewPropertyAnimatorCompat = {
        animation
            .setDuration( 150 )
            .rotation( -35 )
            .scaleX( 0 )
            .scaleY( 0 )
    }
}