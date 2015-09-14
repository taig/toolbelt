package io.taig.android.graphic

import android.annotation.{ SuppressLint, TargetApi }
import android.os.Build
import android.os.Build.VERSION.SDK_INT
import android.view.Display
import io.taig.android.graphic._

import scala.language.reflectiveCalls
import scala.reflect.ClassTag

/**
 * A simple dimension with width and height
 */
case class Dimension[T: Numeric]( width: T, height: T )
        extends Pair[T]( width, height ) {
    override type S = Dimension[T]

    override protected def apply( width: T, height: T ) = Dimension[T]( width, height )

    override def toString = s"$width x $height"
}

object Dimension {
    def apply[T: Numeric]( array: Array[T] ): Dimension[T] = {
        require( array.length == 2 )

        Dimension( array( 0 ), array( 1 ) )
    }

    def apply[T: Numeric: ClassTag]( f: Array[T] ⇒ Unit ): Dimension[T] = {
        val array = new Array[T]( 2 )
        f( array )
        Dimension( array )
    }

    @SuppressLint( Array( "NewApi" ) )
    def apply( display: Display ): Dimension[Int] = {
        if ( SDK_INT >= 13 ) {
            Dimension( display.getSize _ )
        } else {
            display.dimension
        }
    }

    @TargetApi( 13 )
    def apply( f: android.graphics.Point ⇒ Unit ): Dimension[Int] = {
        val point = new android.graphics.Point
        f( point )
        Dimension( point.x, point.y )
    }
}