package com.taig.android

import java.io.{ByteArrayOutputStream, File}

import android.graphics.Bitmap
import android.net.Uri
import android.util.Base64
import com.taig.android.graphic.Resolution

import scala.util.Try

package object io
{
	implicit class RichBitmap( bitmap: Bitmap )
	{
		def getResolution() = Resolution( bitmap.getWidth, bitmap.getHeight )

		def toBase64(): String =
		{
			val stream = new ByteArrayOutputStream()

			try
			{
				bitmap.compress( Bitmap.CompressFormat.PNG, 100, stream )
				Base64.encodeToString( stream.toByteArray, Base64.DEFAULT )
			}
			finally
			{
				stream.close()
			}
		}
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

		def isImage() = toImage().isSuccess

		def toImage() = Try( new Image( file ) )
	}
}