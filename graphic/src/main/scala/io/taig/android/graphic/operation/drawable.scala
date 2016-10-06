package io.taig.android.graphic.operation

import android.content.Context
import android.graphics.drawable.Drawable

final class drawable( drawable: Drawable )

object drawable {
    final class companion( drawable: Drawable.type ) {
        /**
         * Summon a Drawable from an xml attr definition (such as
         * `R.attr.selectableItemBackground`)
         */
        def fromAttribute( id: Int )( implicit c: Context ): Drawable = {
            val array = Array( id )
            val attributes = c.obtainStyledAttributes( array )

            try {
                attributes.getDrawable( 0 )
            } finally {
                attributes.recycle()
            }
        }
    }
}