package com.taig.android.service.wallpaper

import android.content.Context
import android.graphics.Canvas
import android.os.Bundle
import android.util.Log
import android.view.{SurfaceHolder, MotionEvent}
import com.taig.android.Resolution
import com.taig.android.service.wallpaper.Driver.Tag

abstract class Driver( val context: Context )
{
	private var surface: Option[SurfaceHolder] = None

	val touchEvents: Boolean

	val offsetNotifications: Boolean

	def onCreate( surface: SurfaceHolder, resolution: Resolution )
	{
		this.surface = Some( surface )
	}

	def onStart( resolution: Resolution ) {}

	def onRestart() {}

	def onStop() {}

	def onDestroy()
	{
		surface = None
	}

	def onTouchEvent( event: MotionEvent ) {}

	def onOffsetsChanged( x: Float, y: Float, xStep: Float, yStep: Float, xPixel: Int, yPixel: Int ) {}

	def onCommand( action: String, x: Int, y: Int, z: Int, extras: Bundle, resultRequested: Boolean ): Option[Bundle] =
	{
		None
	}

	def onDesiredSizeChanged( width: Int, height: Int ) {}

	def draw(): Unit = surface.map( surface =>
	{
		var canvas: Option[Canvas] = None

		try
		{
			canvas = Option( surface.lockCanvas() )
			canvas.map( draw )
		}
		finally
		{
			try
			{
				canvas.map( surface.unlockCanvasAndPost )
			}
			catch
			{
				case exception: IllegalArgumentException =>
				{
					Log.w( Tag, "Weird on device rotation surface exception thrown and ignored" )
				}
			}
		}
	} )

	protected def draw( canvas: Canvas ): Unit
}

object Driver
{
	val Tag = classOf[Driver].getName
}