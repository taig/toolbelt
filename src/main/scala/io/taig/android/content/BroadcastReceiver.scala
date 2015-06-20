package io.taig.android.content

import android.content.Context

trait	BroadcastReceiver
extends android.content.BroadcastReceiver
{
	override final def onReceive( context: Context, intent: android.content.Intent ) = onReceive( intent )( context )

	def onReceive( intent: android.content.Intent )( implicit context: Context ): Unit
}