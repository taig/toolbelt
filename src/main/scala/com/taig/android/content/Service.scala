package com.taig.android.content

trait	Service
extends	android.app.Service
with	Contextual
{
	override implicit def context = getApplicationContext
}