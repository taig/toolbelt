package io.taig.android.widget.ops

import android.view.animation.TranslateAnimation
import io.taig.android.R
import io.taig.android.content._
import io.taig.android.graphic.Direction._
import io.taig.android.graphic._

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

	def slideIn( from: Direction, duration: Duration = 350 milliseconds, delay: Duration = 250 milliseconds ) =
	{
		val window = Dimension( view.getContext.WindowManager.getDefaultDisplay )
		val position = getAndStorePosition
		val dimension = view.dimension

		val transition = from match
		{
			case Left => new TranslateAnimation( -( position + dimension ).x, 0, 0, 0 )
			case Top => new TranslateAnimation( 0, 0, -( position + dimension ).y, 0 )
			case Right => new TranslateAnimation( -( position - window ).x, 0, 0, 0 )
			case Bottom => new TranslateAnimation( 0, 0, -( position - window ).y, 0 )
		}

		transition.setStartOffset( delay.toMillis )
		transition.setDuration( duration.toMillis )
		transition.setFillAfter( true )

		view.startAnimation( transition )
	}

	def slideOut( to: Direction, duration: Duration = 350 milliseconds, delay: Duration = 250 milliseconds ) =
	{
		val window = Dimension( view.getContext.WindowManager.getDefaultDisplay )
		val position = getAndStorePosition
		val dimension = view.dimension

		val transition = to match
		{
			case Left => new TranslateAnimation( 0, -( position + dimension ).x, 0, 0 )
			case Top => new TranslateAnimation( 0, 0, 0, -( position + dimension ).y )
			case Right => new TranslateAnimation( 0, -( position - window ).x, 0, 0 )
			case Bottom => new TranslateAnimation( 0, 0, 0, -( position - window ).y )
		}

		transition.setStartOffset( delay.toMillis )
		transition.setDuration( duration.toMillis )
		transition.setFillAfter( true )

		view.startAnimation( transition )
	}
}