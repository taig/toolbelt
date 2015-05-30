package io.taig.android.content

import android.content.{BroadcastReceiver, Context}

trait	Receiver
extends BroadcastReceiver
{
	override final def onReceive( context: Context, intent: android.content.Intent ) = onReceive( intent )( context )

	def onReceive( intent: android.content.Intent )( implicit context: Context ): Unit
}