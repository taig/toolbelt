package com.taig.android.io

import android.content.Context
import android.graphics.{Bitmap, BitmapFactory, Matrix}
import android.os.Build
import com.taig.android.Resolution
import com.taig.android.io.BitmapLoader.{Key, Value}

import scala.collection.mutable

class BitmapLoader( val context: Context )
{
	private val bitmaps = mutable.Map.empty[Key, Value]

	/**
	 * Force to load Bitmap from disk and ignore the cache, however the cache will be updated with the retrieved
	 * resource
	 *
	 * @param resource The drawable id to load
	 * @return The Bitmap object from disk
	 */
	def load( resource: Int ): Bitmap =
	{
		load( resource, getImageResolution( resource ) )
	}

	/**
	 * Force to load Bitmap from disk and ignore the cache, however the cache will be updated with the retrieved
	 * resource
	 *
	 * @param resource The drawable id to load
	 * @param target   The resolution the image will be down-sampled and -scaled to
	 * @return The Bitmap object from disk
	 */
	def load( resource: Int, target: Resolution ): Bitmap =
	{
		val resolution = getImageResolution( resource )

		// Determine downsampling value
		val options = new BitmapFactory.Options()
		options.inSampleSize = getSampleSize( resolution, target )
		options.inScaled = false

		if( Build.VERSION.SDK_INT >= 10 )
		{
			options.inPreferQualityOverSpeed = true
		}

		var bitmap = BitmapFactory.decodeResource( context.getResources, resource, options )

		if( !resolution.equals( target ) )
		{
			// Determine scaling ratio: Which edge (top or left) is closer to the screen edge?
			val ratio = if( bitmap.getWidth / bitmap.getHeight.toFloat <= target.width / target.height.toFloat )
			{
				target.width / bitmap.getWidth.toFloat
			}
			else
			{
				target.height / bitmap.getHeight.toFloat
			}

			// (Down-) scale the Bitmap to match the screen size in either width or height (whatever is closer) and crop
			// overhanging areas
			val scaled = Resolution( ( target.width / ratio ).toInt, ( target.height / ratio ).toInt )
			val x = if( bitmap.getWidth > scaled.width ) ( bitmap.getWidth - scaled.width ) / 2 else 0
			val y = if( bitmap.getHeight > scaled.height ) ( bitmap.getHeight - scaled.height ) / 2 else 0

			val matrix = new Matrix()
			matrix.setScale( ratio, ratio )

			bitmap = Bitmap.createBitmap(
				bitmap,
				x,
				y,
				bitmap.getWidth - 2 * x,
				bitmap.getHeight - 2 * y,
				matrix,
				false
			)
		}

		bitmaps.put( Key( resource, target ), Value( bitmap ) )
		bitmap
	}

	/**
	 * Retrieve Bitmap from cache, if not possible load from disk and store in cache
	 *
	 * @param resource The drawable id to load
	 * @return The Bitmap object from cache or disk
	 */
	def get( resource: Int ): Bitmap = get( resource, getImageResolution( resource ) )

	/**
	 * Retrieve Bitmap from cache, if not possible load from disk, downscale it to the target resolution and store it
	 * in cache
	 *
	 * @param resource The drawable id to load
	 * @param target   The resolution the image will be down-sampled and -scaled to
	 * @return The Bitmap object from cache or disk
	 */
	def get( resource: Int, target: Resolution ): Bitmap =
	{
		val key = Key( resource, target )

		bitmaps.get( key ).fold( load( resource, target ) )( cache =>
		{
			if( cache.bitmap.isRecycled )
			{
				bitmaps.remove( key )
				get( resource, target )
			}
			else
			{
				cache.users += 1
				cache.bitmap
			}
		} )
	}

	def release( resource: Int ): Unit = release( resource, getImageResolution( resource ) )

	def release( resource: Int, resolution: Resolution )
	{
		val key = Key( resource, resolution )

		bitmaps.get( key ).map( cache =>
		{
			cache.users -= 1

			if( cache.users == 0 )
			{
				cache.bitmap.recycle()
				bitmaps.remove( key )
			}
		} )
	}

	def release( bitmap: Bitmap )
	{
		for( ( Key( resource, resolution ), Value( x, _ ) ) <- bitmaps )
		{
			if( bitmap == x )
			{
				release( resource, resolution )
				return
			}
		}
	}

	def destroy()
	{
		bitmaps.values.foreach( _.bitmap.recycle )
		bitmaps.clear()
	}

	private def getImageResolution( resource: Int ) =
	{
		val options = new BitmapFactory.Options
		options.inJustDecodeBounds = true
		options.inScaled = false

		BitmapFactory.decodeResource( context.getResources, resource, options )
		Resolution( options.outWidth, options.outHeight )
	}

	private def getSampleSize( resolution: Resolution, target: Resolution, sampleSize: Int = 1 ): Int =
	{
		if( resolution.width / ( sampleSize * 2 ) >= target.width && resolution.height / ( sampleSize * 2 ) >= target.width )
		{
			getSampleSize( resolution, target, sampleSize * 2 )
		}
		else
		{
			sampleSize
		}

	}
}

object BitmapLoader
{
	val Tag = classOf[BitmapLoader].getName

	private case class Key( resource: Int, resolution: Resolution )

	private case class Value( bitmap: Bitmap, var users: Int = 1 )
}