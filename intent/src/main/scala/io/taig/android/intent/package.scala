package io.taig.android

import android.content.{Context, Intent}
import android.net.Uri

import scala.reflect.ClassTag

package object intent {
  type Intent = android.content.Intent

  object Intent {
    def apply(): Intent = new Intent()

    def apply(intent: Intent): Intent = new Intent(intent)

    def apply(action: String): Intent = new Intent(action)

    def apply(action: String, uri: Uri): Intent = new Intent(action, uri)

    def apply(`class`: Class[_])(implicit c: Context): Intent =
      new Intent(c, `class`)

    def apply[T](implicit c: Context, tag: ClassTag[T]): Intent = {
      new Intent(c, implicitly[ClassTag[T]].runtimeClass)
    }

    def apply(action: String, uri: Uri, `class`: Class[_])(
        implicit c: Context): Intent = {
      new Intent(action, uri, c, `class`)
    }
  }
}
