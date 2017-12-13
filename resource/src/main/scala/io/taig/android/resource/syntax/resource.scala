package io.taig.android.resource.syntax

import io.taig.android.resource.{Quantity, operation}

import scala.language.implicitConversions

trait resource {
  implicit def resourceIntSyntax(id: Int): operation.resource[Int] = {
    new operation.resource[Int](id)
  }

  implicit def resourceQuantitySyntax(
      quantity: Quantity): operation.resource[Quantity] = {
    new operation.resource[Quantity](quantity)
  }
}

object resource extends resource
