package io.taig.android.util.syntax

import android.os.Bundle
import io.taig.android.util.operation

import scala.language.implicitConversions

trait bundle {
    implicit def utilBundleSyntax( bundle: Bundle ): operation.bundle = {
        new operation.bundle( bundle )
    }
}

object bundle extends bundle