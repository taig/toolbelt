package com.taig.android.service

import android.content.Context
import android.graphics.{Paint, Canvas}
import android.os.Build.VERSION
import android.os.Bundle
import android.service.wallpaper.WallpaperService
import android.util.Log
import android.view.{MotionEvent, SurfaceHolder}
import com.taig.android.Contextual
import com.taig.android.service.Wallpaper.Driver
import com.taig.android.util.Resolution

import scala.collection.mutable

abstract class Wallpaper extends WallpaperService with Contextual
{
	private val proxies = mutable.ListBuffer.empty[Proxy]

	override def onCreate()
	{
		super.onCreate()
	}

	override def onDestroy()
	{
		super.onDestroy()

		proxies.foreach( _.defuel )
	}

	override final def onCreateEngine = new Proxy( load )

	def load: Driver

	protected final def inject( load: () => Driver )
	{
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

//			setTouchEventsEnabled( driver.touchEvents )
//			setOffsetNotificationsEnabled( driver.offsetNotifications )

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

			proxies += this
		}

		override def onDestroy()
		{
			super.onDestroy()

			defuel

			proxies -= this
		}

		override def onVisibilityChanged( visible: Boolean )
		{
			super.onVisibilityChanged( visible )

			driver.foreach( driver => ( visible, state ) match
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
				case _ => //
			} )
		}

		override def onTouchEvent( event: MotionEvent )
		{
			super.onTouchEvent( event )

			driver.map( _.onTouchEvent( event ) )
		}

		override def onOffsetsChanged( x: Float, y: Float, xStep: Float, yStep: Float, xPixel: Int, yPixel: Int )
		{
			super.onOffsetsChanged( x, y, xStep, yStep, xPixel, yPixel )

			driver.map( _.onOffsetsChanged( x, y, xStep, yStep, xPixel, yPixel ) )
		}

		override def onCommand( action: String, x: Int, y: Int, z: Int, extras: Bundle, resultRequested: Boolean ) =
		{
			driver.map( _.onCommand( action, x, y, z, extras, resultRequested ) ).flatten.orNull
		}

		override def onDesiredSizeChanged( width: Int, height: Int )
		{
			super.onDesiredSizeChanged( width, height )

			driver.map( _.onDesiredSizeChanged( width, height ) )
		}

		override def onSurfaceChanged( surface: SurfaceHolder, format: Int, width: Int, height: Int )
		{
			super.onSurfaceChanged( surface, format, width, height )

			resolution = Resolution( width, height )

			driver.map( _.onCreate( surface, resolution ) )
			state = State.Create
		}

		override def onSurfaceDestroyed( holder: SurfaceHolder )
		{
			super.onSurfaceDestroyed( holder )

			defuel
		}
	}
}

object Wallpaper
{
	val Tag = classOf[Wallpaper].getName

	abstract class Driver( context: Context )
	{
		private var surface: Option[SurfaceHolder] = None

		//		val touchEvents: Boolean
		//
		//		val offsetNotifications: Boolean

		def onCreate( surface: SurfaceHolder, resolution: Resolution )
		{
			this.surface = Some( surface )
		}

		def onStart() {}

		def onRestart() {}

		def onStop() {}

		def onDestroy() { surface = None }

		def onTouchEvent( event: MotionEvent ) {}

		def onOffsetsChanged( x: Float, y: Float, xStep: Float, yStep: Float, xPixel: Int, yPixel: Int ) {}

		def onCommand( action: String, x: Int, y: Int, z: Int, extras: Bundle, resultRequested: Boolean ): Option[Bundle] = None

		def onDesiredSizeChanged( width: Int, height: Int ) {}

		def draw(): Unit = surface.map( surface =>
		{
			var canvas: Option[Canvas] = None

			try
			{
				canvas = Option( surface.lockCanvas( ) )
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

		case class Empty( context: Context ) extends Driver( context )
		{
			override def onStart()
			{
				super.onStart( )
				draw()
			}

			override protected def draw( canvas: Canvas )
			{
				// Paint it black
				canvas.drawColor( context.getResources.getColor( android.R.color.black ) )
			}
		}
	}
}