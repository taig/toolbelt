package io.taig.android

import scala.reflect._

package object util {
    def identify[T: ClassTag]: Int = classTag[T].runtimeClass.getName.hashCode
}
