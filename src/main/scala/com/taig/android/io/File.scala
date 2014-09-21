package com.taig.android.io

import java.io.File.separator
import java.net.URI
import java.{io => java}

import android.graphics.Bitmap.createBitmap
import android.graphics.{Bitmap, BitmapFactory, Matrix}
import android.graphics.BitmapFactory.Options
import android.util.Log
import com.taig.android.graphic.{Area, Resolution}

object File
{
	class Image( path: String ) extends java.File( path )
	{
		def this( base: String, name: String ) = this( base + separator + name )

		def this( base: java.File, name: String ) = this( base.getPath + separator + name )

		def this( uri: URI ) = this( uri.getPath )

		val resolution =
		{
			val options = new Options
			{
				inJustDecodeBounds = true
				inScaled = false
			}

			BitmapFactory.decodeFile( getPath, options )

			Resolution( options.outWidth, options.outHeight )
		}

		require( resolution.width > -1 && resolution.height > -1, "Not a valid image file" )

		def decode(): Bitmap = decode( getDefaultOptions )

		def decode( options: Options ): Bitmap = BitmapFactory.decodeFile( getPath, options )

		def decode( scale: Float ): Bitmap = decode( scale, getDefaultOptions )

		def decode( scale: Float, options: Options ): Bitmap = decode( scale, Area( ( 0, 0 ), resolution ), options )

		def decode( clipping: Area ): Bitmap = decode( clipping, getDefaultOptions )

		def decode( clipping: Area, options: Options ): Bitmap = decode( 1, clipping, options )

		def decode( scale: Float, clipping: Area, options: Options = getDefaultOptions ): Bitmap =
		{
			// When the sample size has not been set we'll make a sampled decoding part of the scaling process.
			if( scale != 1 && options.inSampleSize == 0 )
			{
				// Calculate the sample size
				options.inSampleSize = Stream
					.from( 0 )
					.map( Math.pow( 2, _ ).toInt )
					.takeWhile( _ <= 1 / scale )
					.last
			}

			val bitmap = decode( options )

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

	object Image
	{
//		def getSampleSize( image: Resolution, target: Resolution ) =
//		{
//			def getSampleSize( sampleSize: Int ): Int =
//			{
//				if( image.width / ( sampleSize * 2 ) >= target.width && image.height / ( sampleSize * 2 ) >= target.width )
//				{
//					getSampleSize( sampleSize * 2 )
//				}
//				else
//				{
//					sampleSize
//				}
//			}
//
//			getSampleSize( 1 )
//		}
	}
}