package io.taig.android.graphic

import io.taig.android.Parcelable

@Parcelable
trait Alignable
{
	def resolve( resolution: Dimension, target: Dimension ): Positionable

	def clip( resolution: Dimension, target: Dimension ): Area
}