package com.taig.android

import java.io.File

import android.graphics.Bitmap
import com.taig.android.graphic.Resolution
import com.taig.android.io.File.Image

import scala.util.Try

package object io
{
	implicit class RichBitmap( bitmap: Bitmap )
	{
		lazy val resolution = Resolution( bitmap.getWidth, bitmap.getHeight )
	}

	implicit class RichFile( file: File )
	{
		def deleteRecursively()
		{
			def deleteRecursively( file: File )
			{
				if( file.isDirectory )
				{
					file.listFiles().foreach( deleteRecursively )
				}
				else
				{
					file.delete()
				}
			}

			deleteRecursively( file )
		}

		def asImage = Try( new Image( file.getPath ) )
	}
}