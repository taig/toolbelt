package io.taig.android.graphic.operation

import io.taig.android.graphic.Dimension

final class numeric[T: Numeric]( a: T ) {
    def x( b: T ): Dimension[T] = Dimension( a, b )
}