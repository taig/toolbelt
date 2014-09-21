package com.taig.android.graphic

trait Alignable
{
	def resolve( resolution: Resolution, target: Resolution ): Positionable
}