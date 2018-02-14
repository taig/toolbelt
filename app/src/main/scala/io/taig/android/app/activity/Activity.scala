package io.taig.android.app.activity

import android.support.v7.app.AppCompatActivity
import io.taig.android.context.Contextual

trait Activity extends AppCompatActivity with Contextual {
  override implicit def context: this.type = this
}
