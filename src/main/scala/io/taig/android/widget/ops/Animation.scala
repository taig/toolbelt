package io.taig.android.widget.ops

import android.view.animation.TranslateAnimation
import io.taig.android.R
import io.taig.android.content._
import io.taig.android.graphic.Direction._
import io.taig.android.graphic._

import scala.concurrent.duration.Duration.Zero
import scala.concurrent.duration._
import scala.language.postfixOps

trait Animation
{
	def view: android.view.View

	private def storePosition(): Point[Int] =
	{
		val position = Point( view.getLocationInWindow _ )
		view.setTag( R.id.view_initial_position, position )
		position
	}

	private def getPosition = Option( view.getTag( R.id.view_initial_position ).asInstanceOf[Point[Int]] )

	private def getAndStorePosition = getPosition.getOrElse( storePosition() )

	def slideIn( from: Direction, duration: Duration = 350 milliseconds, delay: Duration = Zero ) =
	{
		val animation = ( window: Dimension[Int], area: Area[Int] ) => from match
		{
			case Left => new TranslateAnimation( area.edge( Right ).distanceTo( window ).left, 0, 0, 0 )
			case Top => new TranslateAnimation( 0, 0, area.edge( Bottom ).distanceTo( window ).top, 0 )
			case Right => new TranslateAnimation( area.edge( Left ).distanceTo( window ).right, 0, 0, 0 )
			case Bottom => new TranslateAnimation( 0, 0, area.edge( Top ).distanceTo( window ).bottom, 0 )
		}

		animate( animation, duration, delay )
	}

	def slideOut( to: Direction, duration: Duration = 350 milliseconds, delay: Duration = Zero ) =
	{
		val animation = ( window: Dimension[Int], area: Area[Int] ) => to match
		{
			case Left => new TranslateAnimation( 0, area.edge( Right ).distanceTo( window ).left, 0, 0 )
			case Top => new TranslateAnimation( 0, 0, 0, area.edge( Bottom ).distanceTo( window ).top )
			case Right => new TranslateAnimation( 0, area.edge( Left ).distanceTo( window ).right, 0, 0 )
			case Bottom => new TranslateAnimation( 0, 0, 0, area.edge( Top ).distanceTo( window ).bottom )
		}

		animate( animation, duration, delay )
	}

	private def animate( animation: ( Dimension[Int], Area[Int] ) => TranslateAnimation, duration: Duration, delay: Duration ): Unit =
	{
		val window = Dimension( view.getContext.WindowManager.getDefaultDisplay )
		val area = Area( getAndStorePosition, view.dimension )

		val transition = animation( window, area )

		transition.setStartOffset( delay.toMillis )
		transition.setDuration( duration.toMillis )
		transition.setFillAfter( true )

		view.startAnimation( transition )
	}
}