package io.taig.android.util.syntax

import java.io.File

import io.taig.android.util.operation

import scala.language.implicitConversions

trait file {
    implicit def utilFileSyntax( file: File ): operation.file = {
        new operation.file( file )
    }
}

object file extends file