package io.taig.android.app.broadcast

import android.content.Context

trait Receiver extends android.content.BroadcastReceiver {
  override final def onReceive(context: Context,
                               intent: android.content.Intent) = {
    onReceive(intent)(context)
  }

  def onReceive(intent: android.content.Intent)(implicit c: Context): Unit
}
