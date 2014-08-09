package com.taig.android.content

import android.content.Context

trait Contextual
{
	protected implicit val context: Context

	def getExternalOrInternalCacheDir = Option( context.getExternalCacheDir ).getOrElse( context.getCacheDir )
}