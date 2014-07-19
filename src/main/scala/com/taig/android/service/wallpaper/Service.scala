package com.taig.android.service.wallpaper

import android.os.Build.VERSION
import android.os.Bundle
import android.service.wallpaper.WallpaperService
import android.util.Log
import android.view.{MotionEvent, SurfaceHolder}
import com.taig.android.service.wallpaper.Service.Tag
import scala.collection.mutable

abstract class Service extends WallpaperService
{
	private val proxies = mutable.ListBuffer.empty[Proxy]

	override def onCreate()
	{
		super.onCreate()

		Log.d( Tag, "Service: onCreate" )
	}

	override def onDestroy()
	{
		super.onDestroy()

		Log.d( Tag, "Service: onDestroy" )

		proxies.foreach( _.defuel )
	}

	override final def onCreateEngine =
	{
		Log.d( Tag, "Service: onCreateEngine" )

		new Proxy( load )
	}

	def load: Driver

	protected final def inject( load: () => Driver )
	{
		Log.d( Tag, "Service: inject" )

		proxies.foreach( _.fuel( load() )  )
	}

	class Proxy() extends Engine
	{
		private object State extends Enumeration
		{
			type State = Value

			val Undefined, Init, Create, Start, Stop, Destroyed = Value
		}

		def this( driver: Driver ) =
		{
			this()
			fuel( driver )
		}

		private var driver: Option[Driver] = None

		private var resolution = Resolution( 0, 0 )

		private var state = State.Undefined

		override def setOffsetNotificationsEnabled( enabled: Boolean )
		{
			if( VERSION.SDK_INT >= 15 )
			{
				super.setOffsetNotificationsEnabled( true )
			}
		}

		def fuel( driver: Driver )
		{
			defuel

			setTouchEventsEnabled( driver.touchEvents )
			setOffsetNotificationsEnabled( driver.offsetNotifications )

			this.driver = Some( driver )
			this.state = State.Init

			if( isVisible )
			{
				onVisibilityChanged( true )
			}
		}

		def defuel
		{
			driver.map( driver =>
			{
				if( state == State.Start )
				{
					driver.onStop()
					state = State.Stop
				}
	
				if( state == State.Stop )
				{
					driver.onDestroy()
					state = State.Destroyed
				}
			} )

			driver = None
			state = State.Undefined
		}

		override def onCreate( surfaceHolder: SurfaceHolder )
		{
			super.onCreate( surfaceHolder )

			Log.d( Tag, "Proxy: onCreate" )

			proxies += this
		}

		override def onDestroy()
		{
			super.onDestroy()

			Log.d( Tag, "Proxy: onDestroy" )

			defuel

			proxies -= this
		}

		override def onVisibilityChanged( visible: Boolean )
		{
			super.onVisibilityChanged( visible )

			Log.d( Tag, s"Proxy: onVisibilityChanged( $visible )" )

			driver.map( driver =>
			{
				( visible, state ) match
				{
					case ( true, State.Init ) =>
					{
						driver.onCreate( getSurfaceHolder, resolution )
						driver.onStart()
						this.state = State.Start
					}
					case ( true, State.Create ) =>
					{
						driver.onStart()
						this.state = State.Start
					}
					case ( true, State.Stop ) =>
					{
						driver.onRestart()
						driver.onStart()
						this.state = State.Start
					}
					case ( false, State.Start ) =>
					{
						driver.onStop()
						this.state = State.Stop
					}
					case ( visible, state ) =>
					{
						Log.d( Tag, s"Proxy: No action to perform with visible: $visible, state: $state (${driver.getClass.getSimpleName}, ${driver.id}})" )
					}
				}
			} )
		}

		override def onTouchEvent( event: MotionEvent )
		{
			super.onTouchEvent( event )

			Log.d( Tag, "Proxy: onTouchEvent" )

			driver.map( _.onTouchEvent( event ) )
		}

		override def onOffsetsChanged( x: Float, y: Float, xStep: Float, yStep: Float, xPixel: Int, yPixel: Int )
		{
			super.onOffsetsChanged( x, y, xStep, yStep, xPixel, yPixel )

			Log.d( Tag, s"Proxy: onOffsetsChanged( $x, $y, $xStep, $yStep, $xPixel, $yPixel )" )

			driver.map( _.onOffsetsChanged( x, y, xStep, yStep, xPixel, yPixel ) )
		}

		override def onCommand( action: String, x: Int, y: Int, z: Int, extras: Bundle, resultRequested: Boolean ) =
		{
			Log.d( Tag, s"Proxy: onCommand( $action )" )

			driver.map( _.onCommand( action, x, y, z, extras, resultRequested ) ).flatten.orNull
		}

		override def onDesiredSizeChanged( width: Int, height: Int )
		{
			super.onDesiredSizeChanged( width, height )

			Log.d( Tag, s"Proxy: onDesiredSizeChanged( $width, $height )" )

			driver.map( _.onDesiredSizeChanged( width, height ) )
		}

		override def onSurfaceChanged( surface: SurfaceHolder, format: Int, width: Int, height: Int )
		{
			super.onSurfaceChanged( surface, format, width, height )

			Log.d( Tag, s"Proxy: onSurfaceChanged( $format, $width, $height )" )

			resolution = Resolution( width, height )

			driver.map( _.onCreate( surface, resolution ) )
			state = State.Create
		}

		override def onSurfaceRedrawNeeded( holder: SurfaceHolder )
		{
			super.onSurfaceRedrawNeeded( holder )

			Log.d( Tag, "Proxy: onSurfaceRedrawNeeded" )
		}

		override def onSurfaceCreated( holder: SurfaceHolder )
		{
			super.onSurfaceCreated( holder )

			Log.d( Tag, "Proxy: onSurfaceCreated" )
		}

		override def onSurfaceDestroyed( holder: SurfaceHolder )
		{
			super.onSurfaceDestroyed( holder )

			defuel

			Log.d( Tag, "Proxy: onSurfaceDestroyed" )
		}
	}
}

object Service
{
	val Tag = classOf[Service].getName
}