package io.taig.android.content

trait	Application
extends	android.app.Application
with	Contextual
{
	override implicit def context = this
}