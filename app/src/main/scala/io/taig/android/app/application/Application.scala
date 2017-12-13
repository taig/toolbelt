package io.taig.android.app.application

import io.taig.android.context.Contextual

trait Application extends android.app.Application with Contextual {
  override implicit def context: Application = this
}
