package io.taig.android.system_service.operation

import android.content.Context
import io.taig.android.system_service.SystemServiceResolver

final class context(context: Context) {
  def service[T: SystemServiceResolver]: T =
    SystemServiceResolver[T].resolve(context)
}
