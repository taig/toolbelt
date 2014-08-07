package com.taig.android

import android.content.Context

trait Contextual
{
	this: Context =>

	protected implicit val context: Context = this

	def getExternalOrInternalCacheDir = Option( getExternalCacheDir ).getOrElse( getCacheDir )
}