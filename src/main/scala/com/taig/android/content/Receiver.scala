package com.taig.android.content

import android.content.{BroadcastReceiver, Context, Intent}

trait	Receiver
extends BroadcastReceiver
{
	override final def onReceive( context: Context, intent: Intent ) = onReceive( intent )( context )

	def onReceive( intent: Intent )( implicit context: Context ): Unit
}