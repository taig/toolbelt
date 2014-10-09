package com.taig.android.graphic

trait Alignable
{
	def resolve( resolution: Resolution, target: Resolution ): Positionable

	def clip( resolution: Resolution, target: Resolution ): Area
}