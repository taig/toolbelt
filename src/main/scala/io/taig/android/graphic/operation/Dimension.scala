package io.taig.android.graphic.operation

import scala.math.Integral.Implicits._
import scala.math.Ordering.Implicits._
import scala.math.{ min, max }
import io.taig.android.graphic

abstract class Dimension[T: Integral] {
    def dimension: graphic.Dimension[T]

    /**
     * Aspect ration of this Resolution
     *
     * @return ( with to height, height to width )
     */
    def aspectRatio = (
        dimension.width.toFloat() / dimension.height.toFloat(),
        dimension.height.toFloat() / dimension.width.toFloat()
    )

    /**
     * Compare with and height of this Resolution with the target
     *
     * @return ( width to target width, height to target height )
     */
    def ratioTo( target: io.taig.android.graphic.Dimension[T] ) = (
        target.width.toFloat() / dimension.width.toFloat(),
        target.height.toFloat() / dimension.height.toFloat()
    )

    /**
     * Down- or upscale this Resolution until one of it's dimensions matches the target
     *
     * @param target The target resolution
     */
    def scaleTo( target: graphic.Dimension[T] ) = {
        lazy val ratio = ratioTo( target )

        val x = if ( dimension > target ) {
            max( ratio._1, ratio._2 )
        } else if ( dimension < target ) {
            min( ratio._1, ratio._2 )
        } else if ( dimension.width > target.width ) {
            ratio._1
        } else if ( dimension.height > target.height ) {
            ratio._2
        } else {
            0
        }

        graphic.Dimension(
            ( dimension.width.toInt() * x ).toInt,
            ( dimension.height.toInt() * x ).toInt
        )
    }
}