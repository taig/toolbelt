package com.taig.android.io

import android.content.Context
import android.graphics.{Bitmap, BitmapFactory, Matrix}
import android.os.Build
import com.taig.android.io.BitmapLoader._
import com.taig.android.util.Resolution

import scala.collection.mutable

class BitmapLoader( val context: Context )
{
	private val bitmaps = mutable.Map.empty[Bitmap, Meta]

	/**
	 * Force to load Bitmap from disk and ignore the cache, however the cache will be updated with the retrieved
	 * resource
	 *
	 * @param resource The drawable id to load
	 * @return The Bitmap object from disk
	 */
	def load( resource: Int ): Bitmap = load( resource, getImageResolution( resource ) )

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

			bitmap = Bitmap.createBitmap(
				bitmap,
				x,
				y,
				bitmap.getWidth - 2 * x,
				bitmap.getHeight - 2 * y,
				new Matrix() { setScale( ratio, ratio ) },
				false
			)

			bitmaps.put(
				bitmap,
				Meta( resource, target, ratio / options.inSampleSize, x * options.inSampleSize, y * options.inSampleSize )
			)
		}
		else
		{
			bitmaps.put( bitmap, Meta( resource, target, 1 / options.inSampleSize, 0, 0 ) )
		}

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
	def get( resource: Int, target: Resolution ): Bitmap = bitmaps
		.find{ case ( _, meta ) => resource == meta.resource && target == meta.resolution }
		.fold( load( resource, target ) )
		{
			case ( bitmap, meta ) =>
			{
				if( bitmap.isRecycled )
				{
					bitmaps.remove( bitmap )
					get( resource, target )
				}
				else
				{
					meta.users += 1
					bitmap
				}
			}
		}

	def release( resource: Int ): Unit = release( resource, getImageResolution( resource ) )

	def release( resource: Int, resolution: Resolution ): Unit = bitmaps
		.find{ case ( _, meta ) => resource == meta.resource && resolution == meta.resolution }
		.map
		{
			case ( bitmap, meta ) =>
			{
				meta.users -= 1
				
				if( meta.users == 0 )
				{
					bitmap.recycle()
					bitmaps.remove( bitmap )
				}
			}
		}

	def release( bitmap: Bitmap ): Unit = bitmaps.get( bitmap ).map( meta => release( meta.resource, meta.resolution ) )

	def destroy()
	{
		bitmaps.keys.foreach( _.recycle )
		bitmaps.clear()
	}

	def getMeta( bitmap: Bitmap ) = bitmaps.get( bitmap )

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

	case class Meta( resource: Int, resolution: Resolution, scale: Float, offsetX: Int, offsetY: Int, var users: Int = 1 )
}