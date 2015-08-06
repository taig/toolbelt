package io.taig.android.graphic

object Direction {
    sealed trait Direction
    case object Left extends Direction
    case object Top extends Direction
    case object Right extends Direction
    case object Bottom extends Direction
}