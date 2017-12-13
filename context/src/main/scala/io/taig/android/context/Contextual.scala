package io.taig.android.context

import android.content.Context
import io.taig.android.log.Log

trait Contextual {
  implicit def context: Context

  implicit val Tag: Log.Tag = Log.Tag(getClass.getName)
}
