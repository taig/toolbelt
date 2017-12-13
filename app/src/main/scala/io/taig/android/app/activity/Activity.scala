package io.taig.android.app.activity

import io.taig.android.context.Contextual

trait Activity extends android.app.Activity with Contextual {
  override implicit def context: this.type = this
}
