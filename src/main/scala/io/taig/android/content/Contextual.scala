package io.taig.android.content

import android.content.Context
import io.taig.android.util.Log

trait Contextual {
    implicit def context: Context

    implicit val Tag = Log.Tag( getClass.getName )
}