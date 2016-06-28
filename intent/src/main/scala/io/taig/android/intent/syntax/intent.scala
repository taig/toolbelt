package io.taig.android.intent.syntax

import io.taig.android.intent.{ Intent, operation }

import scala.language.implicitConversions

trait intent {
    implicit def intentSyntax( intent: Intent ): operation.intent = new operation.intent( intent )
}

object intent extends intent