package io.taig.android.resource.operation

import android.content.Context
import io.taig.android.resource.ResourceResolver

final class resource[A](resource: A) {
  def as[B](implicit c: Context, rr: ResourceResolver[A, B]): B = {
    rr.resolve(c, resource, Seq.empty)
  }

  def as[B](arguments: Any*)(implicit c: Context,
                             rr: ResourceResolver[A, B]): B = {
    rr.resolve(c, resource, arguments)
  }
}
