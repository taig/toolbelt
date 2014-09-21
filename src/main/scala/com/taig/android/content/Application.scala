package com.taig.android.content

trait Application extends android.app.Application with Context
{
	override protected[content] implicit val context = this
}