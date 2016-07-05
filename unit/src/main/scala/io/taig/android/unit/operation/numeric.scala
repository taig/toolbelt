package io.taig.android.unit.operation

import android.content.Context
import scala.math.Numeric.Implicits._

final class numeric[T: Numeric]( value: T ) {
    import android.util.TypedValue._

    @inline
    def dp( implicit c: Context ) = {
        applyDimension( COMPLEX_UNIT_DIP, value.toFloat(), c.getResources.getDisplayMetrics )
    }

    @inline
    def dip( implicit c: Context ) = dp

    @inline
    def sp( implicit c: Context ) = {
        applyDimension( COMPLEX_UNIT_SP, value.toFloat(), c.getResources.getDisplayMetrics )
    }
}