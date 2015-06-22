package io.taig.android.content.ops

import io.taig.android.content.Contextual

trait	Unit
extends	Contextual
{
	import android.util.TypedValue._

	def unit: Float

	def dp = applyDimension( COMPLEX_UNIT_DIP, unit, context.getResources.getDisplayMetrics )

	def dip = dp

	def sp = applyDimension( COMPLEX_UNIT_SP, unit, context.getResources.getDisplayMetrics )
}