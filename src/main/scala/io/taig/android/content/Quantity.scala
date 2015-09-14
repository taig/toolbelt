package io.taig.android.content

import android.support.annotation.PluralsRes

/**
 * Helper class to create Android quantity Strings
 */
case class Quantity( @PluralsRes message: Int, count: Int )