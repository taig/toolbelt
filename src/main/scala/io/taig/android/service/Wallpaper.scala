package io.taig.android.service

import android.content.Context
import android.graphics.drawable.Drawable
import android.graphics.{Canvas, Color, ColorFilter}
import android.os.Build.VERSION
import android.os.Bundle
import android.service.wallpaper.WallpaperService
import android.util.Log
import android.view.{MotionEvent, SurfaceHolder}
import io.taig.android.content._
import io.taig.android.graphic.Resolution
import io.taig.android.service.Wallpaper.Driver

import scala.collection.mutable

abstract class Wallpaper extends WallpaperService with Service
{
	private val proxies = mutable.ListBuffer.empty[Proxy]

	override final def onCreateEngine = new Proxy( load )

	def load: Driver

	override def onDestroy()
	{
		super.onDestroy()

		proxies.foreach( _.defuel() )
	}

	def inject( load: => Driver )
	{
		proxies.foreach( _.fuel( load )  )
	}

	class Proxy extends Engine
	{
		private object State extends Enumeration
		{
			type State = Value

			val Undefined, Init, Create, Start, Stop, Destroyed = Value
		}

		private var driver: Option[Driver] = None

		private var resolution: Option[Resolution] = None

		private var state = State.Undefined

		def this( driver: Driver ) =
		{
			this()
			fuel( driver )
		}

		override def setOffsetNotificationsEnabled( enabled: Boolean )
		{
			if( VERSION.SDK_INT >= 15 )
			{
				super.setOffsetNotificationsEnabled( true )
			}
		}

		def fuel( driver: Driver )
		{
			defuel()

			this.driver = Some( driver )
			this.state = State.Init

			update()
		}

		def update( visible: Boolean = isVisible ): Unit = driver.foreach( driver =>
		{
			( state, visible, resolution ) match
			{
				case ( State.Init, _, Some( resolution ) ) =>
					driver.surface = Option( getSurfaceHolder )
					state = State.Create
					driver.onCreate( resolution )
					update( visible )
				case ( State.Create, true, _ ) =>
					state = State.Start
					driver.onStart()
				case ( State.Start, false, _ ) =>
					state = State.Stop
					driver.onStop()
				case ( State.Stop, true, _ ) =>
					state = State.Start
					driver.onRestart()
					driver.onStart()
				case _ => Log.d( Proxy.Tag, s"Could not match ( state: $state, visible: $visible, resolution: $resolution )" )
			}
		} )

		def defuel()
		{
			driver.foreach( driver =>
			{
				if( state == State.Start )
				{
					driver.onStop()
					state = State.Stop
				}
	
				if( state == State.Stop )
				{
					driver.surface = None
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

			defuel()

			proxies -= this
		}

		override def onVisibilityChanged( visible: Boolean )
		{
			super.onVisibilityChanged( visible )

			update( visible )
		}

		override def onTouchEvent( event: MotionEvent )
		{
			super.onTouchEvent( event )

			driver.foreach( _.onTouchEvent( event ) )
		}

		override def onOffsetsChanged( x: Float, y: Float, xStep: Float, yStep: Float, xPixel: Int, yPixel: Int )
		{
			super.onOffsetsChanged( x, y, xStep, yStep, xPixel, yPixel )

			driver.foreach( _.onOffsetsChanged( x, y, xStep, yStep, xPixel, yPixel ) )
		}

		override def onCommand( action: String, x: Int, y: Int, z: Int, extras: Bundle, resultRequested: Boolean ) =
		{
			driver.map( _.onCommand( action, x, y, z, extras, resultRequested ) ).flatten.orNull
		}

		override def onSurfaceChanged( surface: SurfaceHolder, format: Int, width: Int, height: Int )
		{
			super.onSurfaceChanged( surface, format, width, height )

			resolution = Some( Resolution( width, height ) )
			update()
		}

		override def onSurfaceDestroyed( holder: SurfaceHolder )
		{
			super.onSurfaceDestroyed( holder )

			defuel()
		}
	}

	object Proxy
	{
		val Tag = getClass.getName
	}
}

object Wallpaper
{
	val Tag = getClass.getName

	abstract class Driver( implicit context: Context ) extends Drawable
	{
		private[Wallpaper] var surface: Option[SurfaceHolder] = None

		def onCreate( screen: Resolution ) {}

		def onStart() {}

		def onRestart() {}

		def onStop() {}

		def onDestroy() {}

		def onTouchEvent( event: MotionEvent ) {}

		def onOffsetsChanged( x: Float, y: Float, xStep: Float, yStep: Float, xPixel: Int, yPixel: Int ) {}

		def onCommand( action: String, x: Int, y: Int, z: Int, extras: Bundle, resultRequested: Boolean ): Option[Bundle] = None

		def draw(): Unit = surface.foreach( surface =>
		{
			var canvas: Option[Canvas] = None

			try
			{
				canvas = Option( surface.lockCanvas() )
				canvas.foreach( canvas =>
				{
					canvas.drawColor( Color.BLACK )
					draw( canvas )
				} )
			}
			finally
			{
				try
				{
					canvas.foreach( surface.unlockCanvasAndPost )
				}
				catch
				{
					case exception: IllegalArgumentException =>
					{
						Log.w( Tag, "Weird on device rotation surface exception thrown and hereby ignored" )
					}
				}
			}
		} )
	}

	object Driver
	{
		val Tag = getClass.getName

		case class Empty( implicit val context: Context ) extends Driver
		{
			private var color = context.getResources.getColor( android.R.color.black )

			override def onStart() = draw()

			override def setAlpha( alpha: Int ) =
			{
				color = Color.argb( alpha, Color.red( color ), Color.green( color ), Color.blue( color ) )
			}

			override def setColorFilter( filter: ColorFilter ) = {}

			override def getOpacity = Color.alpha( color )

			override protected def draw( canvas: Canvas ) = canvas.drawColor( color )
		}
	}
}