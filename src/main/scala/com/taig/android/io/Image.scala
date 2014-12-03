package com.taig.android.io

import java.io.{BufferedInputStream, InputStream}

import android.content.Context
import android.graphics.Bitmap.createBitmap
import android.graphics.BitmapFactory.Options
import android.graphics.{Bitmap, BitmapFactory, Matrix}
import android.net.Uri
import android.util.Log
import com.taig.android.graphic.{Area, Resolution}

import scala.math._

class Image( val stream: BufferedInputStream )
{
	def this( stream: InputStream ) = this( new BufferedInputStream( stream ) )

	def this( uri: Uri )( implicit context: Context ) = this( context.getContentResolver.openInputStream( uri ) )

	val resolution =
	{
		val options = new Options
		{
			inJustDecodeBounds = true
			inScaled = false
		}

		stream.mark( 1024 )
		BitmapFactory.decodeStream( stream, null, options )
		stream.reset()

		Resolution( options.outWidth, options.outHeight )
	}

	require( resolution.width > -1 && resolution.height > -1, "Not a valid image file" )

	def decode(): Bitmap = decode( getDefaultOptions )

	def decode( target: Resolution ): Bitmap = decode( target, getDefaultOptions )

	def decode( target: Resolution, options: Options ): Bitmap =
	{
		val ( x, y ) = resolution.getRatioTo( target )
		decode( max( x, y ), options )
	}

	def decode( options: Options ): Bitmap = decode( 1f, options )

	def decode( scale: Float ): Bitmap = decode( scale, getDefaultOptions )

	def decode( scale: Float, options: Options ): Bitmap = decode( scale, Area( ( 0, 0 ), resolution ), options )

	def decode( clipping: Area ): Bitmap = decode( clipping, getDefaultOptions )

	def decode( clipping: Area, options: Options ): Bitmap = decode( 1, clipping, options )

	def decode( scale: Float, clipping: Area): Bitmap = decode( scale, clipping, getDefaultOptions )

	def decode( scale: Float, clipping: Area, options: Options ): Bitmap =
	{
		// When the sample size has not been set we'll make a sampled decoding part of the scaling process.
		if( scale != 1 && options.inSampleSize == 0 )
		{
			options.inSampleSize = Stream
				.from( 1 )
				.map( pow( 2, _ ).toInt )
				.takeWhile( _ <= 1 / scale )
				.lastOption
				.getOrElse( 1 )
		}

		val bitmap = BitmapFactory.decodeStream( stream, null, options )
		stream.close()

		// Apply remaining scaling (after sampling has been applied) and clipping
		if( options.inSampleSize > 1 || scale != 1 )
		{
			try
			{
				createBitmap(
					bitmap,
					clipping.position.x / options.inSampleSize,
					clipping.position.y / options.inSampleSize,
					Math.min(
						clipping.resolution.width / options.inSampleSize,
						bitmap.getWidth - clipping.position.x / options.inSampleSize
					),
					Math.min(
						clipping.resolution.height / options.inSampleSize,
						bitmap.getHeight - clipping.position.y / options.inSampleSize
					),
					new Matrix { setScale( options.inSampleSize * scale, options.inSampleSize * scale ) },
					false
				)
			}
			finally
			{
				bitmap.recycle()
			}
		}
		else
		{
			bitmap
		}
	}

	protected def getDefaultOptions = new Options { inScaled = false }
}