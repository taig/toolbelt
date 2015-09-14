package io.taig.android.content

import android.content.Context

/**
 * Helper class to create Android quantity Strings
 */
case class Quantity( message: String, count: Int )

object Quantity {
    def apply( message: Int, count: Int )( implicit context: Context ): Quantity = {
        Quantity( message.as[String], count )
    }
}