package io.taig.android.content.ops

import io.taig.android.content.Contextual
import scala.math.Numeric.Implicits._

abstract class	Unit[T: Numeric]( unit: T )
extends			Contextual
{
	import android.util.TypedValue._

	def dp = applyDimension( COMPLEX_UNIT_DIP, unit.toFloat(), context.getResources.getDisplayMetrics )

	def dip = dp

	def sp = applyDimension( COMPLEX_UNIT_SP, unit.toFloat(), context.getResources.getDisplayMetrics )
}