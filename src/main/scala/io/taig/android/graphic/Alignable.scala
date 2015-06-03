package io.taig.android.graphic

import io.taig.android.Parcelable

@Parcelable
trait Alignable
{
	def resolve( resolution: Resolution, target: Resolution ): Positionable

	def clip( resolution: Resolution, target: Resolution ): Area
}