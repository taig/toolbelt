package io.taig.android.graphic

import io.taig.android.parcelable.annotation.Parcelable

@Parcelable
trait Alignable
{
	def resolve( resolution: Resolution, target: Resolution ): Positionable

	def clip( resolution: Resolution, target: Resolution ): Area
}