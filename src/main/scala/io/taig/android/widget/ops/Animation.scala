package io.taig.android.widget.ops

import android.support.v4.view.{ViewCompat, ViewPropertyAnimatorCompat}
import android.view.animation.{Interpolator, LinearInterpolator}
import io.taig.android.content._
import io.taig.android.graphic.Direction._
import io.taig.android.graphic._

import scala.concurrent.duration.Duration.Zero
import scala.concurrent.duration._
import scala.language.postfixOps

trait Animation
{
	def view: android.view.View

	/**
	 * Places the view out of the window and starts a slide in animation
	 */
	def slideIn( from: Direction, duration: Duration = 250 milliseconds, delay: Duration = Zero, interpolator: Interpolator = new LinearInterpolator() ): ViewPropertyAnimatorCompat =
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

		slide( animation, duration, delay, interpolator )
	}

	/**
	 * Slides the view out of the window from its current position
	 */
	def slideOut( to: Direction, duration: Duration = 250 milliseconds, delay: Duration = Zero, interpolator: Interpolator = new LinearInterpolator() ): ViewPropertyAnimatorCompat =
	{
		val animation = ( window: Dimension[Int], area: Area[Int] ) => to match
		{
			case Left => ViewCompat.animate( view ).translationXBy( -area.edge( Right ).distanceTo( window ).left )
			case Top => ViewCompat.animate( view ).translationYBy( -area.edge( Bottom ).distanceTo( window ).top )
			case Right => ViewCompat.animate( view ).translationXBy( area.edge( Left ).distanceTo( window ).right )
			case Bottom => ViewCompat.animate( view ).translationYBy( area.edge( Top ).distanceTo( window ).bottom )
		}

		slide( animation, duration, delay, interpolator )
	}

	private def slide( animation: ( Dimension[Int], Area[Int] ) => ViewPropertyAnimatorCompat, duration: Duration, delay: Duration, interpolator: Interpolator ) =
	{
		val window = Dimension( view.getContext.WindowManager.getDefaultDisplay )
		val area = Area( Point( view.getLocationInWindow _ ), view.dimension )

		animation( window, area )
			.setDuration( duration.toMillis )
			.setInterpolator( interpolator )
			.setStartDelay( delay.toMillis )
	}

	/**
	 * Sets the view dimensions to 0 width / height and pops back to its actual dimensions
	 */
	def popIn( duration: Duration = 150 milliseconds, delay: Duration = Zero, interpolator: Interpolator = new LinearInterpolator() ): ViewPropertyAnimatorCompat =
	{
		ViewCompat.setScaleX( view, 0 )
		ViewCompat.setScaleY( view, 0 )

		ViewCompat
			.animate( view )
			.setDuration( duration.toMillis )
			.setInterpolator( interpolator )
			.setStartDelay( delay.toMillis )
			.scaleX( 1 )
			.scaleY( 1 )
	}

	/**
	 * Makes the view pop out from its actual dimensions to 0 width / height
	 */
	def popOut( duration: Duration = 150 milliseconds, delay: Duration = Zero, interpolator: Interpolator = new LinearInterpolator() ): ViewPropertyAnimatorCompat =
	{
		ViewCompat
			.animate( view )
			.setDuration( duration.toMillis )
			.setInterpolator( interpolator )
			.setStartDelay( delay.toMillis )
			.scaleX( 0 )
			.scaleY( 0 )
	}
}