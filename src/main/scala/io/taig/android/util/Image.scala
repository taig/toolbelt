package io.taig.android.util

import java.io.{ FileInputStream, File, InputStream }

import android.content.Context
import android.graphics.Bitmap.createBitmap
import android.graphics.BitmapFactory.Options
import android.graphics.{ Bitmap, BitmapFactory, Matrix }
import android.net.Uri
import io.taig.android.graphic._

import scala.math._

class Image private ( stream: ⇒ InputStream ) {
    def this( file: File ) = this( new FileInputStream( file ) )

    def this( uri: Uri )( implicit context: Context ) = this( context.getContentResolver.openInputStream( uri ) )

    val resolution = {
        val options = new Options {
            inJustDecodeBounds = true
            inScaled = false
        }

        val stream = this.stream

        try {
            BitmapFactory.decodeStream( stream, null, options )
        } finally {
            stream.close()
        }

        Dimension[Int]( options.outWidth, options.outHeight )
    }

    require( resolution.width > -1 && resolution.height > -1, "Not a valid image file" )

    def decode(): Bitmap = decode( defaultOptions )

    def decode( matrix: Matrix ): Bitmap = decode( matrix, defaultOptions )

    def decode( target: Dimension[Int] ): Bitmap = decode( target, defaultOptions )

    def decode( target: Dimension[Int], matrix: Matrix ): Bitmap = decode( target, matrix, defaultOptions )

    def decode( target: Dimension[Int], options: Options ): Bitmap = decode( target, null: Matrix, options )

    def decode( target: Dimension[Int], matrix: Matrix, options: Options ): Bitmap = {
        require( target > ( 0 x 0 ), s"Target resolution ($target) must be > ${0 x 0}" )
        val ( x, y ) = resolution.ratioTo( target )
        decode( max( x, y ), options )
    }

    def decode( options: Options ): Bitmap = decode( 1f, options )

    def decode( matrix: Matrix, options: Options ): Bitmap = decode( 1f, options )

    def decode( scale: Float ): Bitmap = decode( scale, defaultOptions )

    def decode( scale: Float, matrix: Matrix ): Bitmap = decode( scale, matrix, defaultOptions )

    def decode( scale: Float, options: Options ): Bitmap = decode( scale, null: Matrix, options )

    def decode( scale: Float, matrix: Matrix, options: Options ): Bitmap = {
        decode( scale, Area( Point.Zero, resolution ), matrix, options )
    }

    def decode( clipping: Area[Int] ): Bitmap = decode( clipping, defaultOptions )

    def decode( clipping: Area[Int], matrix: Matrix ): Bitmap = decode( clipping, matrix, defaultOptions )

    def decode( clipping: Area[Int], options: Options ): Bitmap = decode( 1, clipping, options )

    def decode( clipping: Area[Int], matrix: Matrix, options: Options ): Bitmap = {
        decode( 1, clipping, matrix, options )
    }

    def decode( scale: Float, clipping: Area[Int] ): Bitmap = decode( scale, clipping, defaultOptions )

    def decode( scale: Float, clipping: Area[Int], matrix: Matrix ): Bitmap = {
        decode( scale, clipping, matrix, defaultOptions )
    }

    def decode( scale: Float, clipping: Area[Int], options: Options ): Bitmap = {
        decode( scale, clipping, null, options )
    }

    def decode( scale: Float, clipping: Area[Int], matrix: Matrix, options: Options ): Bitmap = {
        require( scale > 0, s"Scale ($scale) must be > 0" )

        if ( scale != 1 && options.inSampleSize == 0 ) {
            options.inSampleSize = Stream
                .from( 1 )
                .map( pow( 2, _ ).toInt )
                .takeWhile( _ <= 1 / scale )
                .lastOption
                .getOrElse( 1 )
        }

        val stream = this.stream

        try {
            val bitmap = BitmapFactory.decodeStream( this.stream, null, options )

            // Apply remaining scaling (after sampling has been applied) and clipping
            if ( options.inSampleSize > 1 || scale != 1 || matrix != null ) {
                try {
                    createBitmap(
                        bitmap,
                        clipping.position.x / options.inSampleSize,
                        clipping.position.y / options.inSampleSize,
                        Math.min(
                            clipping.dimension.width / options.inSampleSize,
                            bitmap.getWidth - clipping.position.x / options.inSampleSize
                        ),
                        Math.min(
                            clipping.dimension.height / options.inSampleSize,
                            bitmap.getHeight - clipping.position.y / options.inSampleSize
                        ),
                        {
                            val m = Option( matrix ).getOrElse( new Matrix )
                            // TODO check if the given matrix already has a scaling defined
                            m.setScale( options.inSampleSize * scale, options.inSampleSize * scale )
                            m
                        },
                        false
                    )
                } finally {
                    bitmap.recycle()
                }
            } else {
                bitmap
            }
        } finally {
            stream.close()
        }
    }

    protected def defaultOptions = new Options { inScaled = false }
}