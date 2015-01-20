package com.taig.android.graphic

import com.taig.android.parcelable.annotation.Parcelable

@Parcelable
trait Alignable
{
	def resolve( resolution: Resolution, target: Resolution ): Positionable

	def clip( resolution: Resolution, target: Resolution ): Area
}