package io.taig.android.widget.ops

import android.support.v4.view.{ViewCompat, ViewPropertyAnimatorCompat}
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

	private def getOrStorePosition =
	{
		Option( view.getTag( R.id.view_initial_position ).asInstanceOf[Point[Int]] ).getOrElse( storePosition() )
	}

	/**
	 * Places the view out of the window and starts a slide in animation
	 */
	def slideIn( from: Direction, duration: Duration = 250 milliseconds, delay: Duration = Zero ) =
	{
		val animation = ( window: Dimension[Int], area: Area[Int] ) => from match
		{
			case Left =>
			{
				val translation = area.edge( Right ).distanceTo( window ).left
				ViewCompat.setTranslationX( view, -translation )
				ViewCompat.animate( view ).translationXBy( translation )
			}
			case Top =>
			{
				val translation = area.edge( Bottom ).distanceTo( window ).top
				ViewCompat.setTranslationY( view, -translation )
				ViewCompat.animate( view ).translationYBy( translation )
			}
			case Right =>
			{
				val translation = area.edge( Left ).distanceTo( window ).right
				ViewCompat.setTranslationX( view, translation )
				ViewCompat.animate( view ).translationXBy( -translation )
			}
			case Bottom =>
			{
				val translation = area.edge( Top ).distanceTo( window ).bottom
				ViewCompat.setTranslationY( view, translation )
				ViewCompat.animate( view ).translationYBy( -translation )
			}
		}

		animate( animation, duration, delay )
	}

	/**
	 * Slides the view out of the window from its current position
	 */
	def slideOut( to: Direction, duration: Duration = 250 milliseconds, delay: Duration = Zero ) =
	{
		val animation = ( window: Dimension[Int], area: Area[Int] ) => to match
		{
			case Left => ViewCompat.animate( view ).translationXBy( -area.edge( Right ).distanceTo( window ).left )
			case Top => ViewCompat.animate( view ).translationYBy( -area.edge( Bottom ).distanceTo( window ).top )
			case Right => ViewCompat.animate( view ).translationXBy( area.edge( Left ).distanceTo( window ).right )
			case Bottom => ViewCompat.animate( view ).translationYBy( area.edge( Top ).distanceTo( window ).bottom )
		}

		animate( animation, duration, delay )
	}

	private def animate( animation: ( Dimension[Int], Area[Int] ) => ViewPropertyAnimatorCompat, duration: Duration, delay: Duration ): Unit =
	{
		val window = Dimension( view.getContext.WindowManager.getDefaultDisplay )
		val area = Area( getOrStorePosition, view.dimension )

		animation( window, area )
			.setStartDelay( delay.toMillis )
			.setDuration( duration.toMillis )
	}
}