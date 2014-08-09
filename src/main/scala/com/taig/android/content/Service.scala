package com.taig.android.content

trait Service extends android.app.Service with Contextual
{
	override protected implicit val context = this
}