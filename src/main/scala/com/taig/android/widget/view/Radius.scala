package com.taig.android.widget.view

import android.content.res.TypedArray
import android.graphics.PorterDuff.Mode
import android.graphics._
import android.view.View
import com.taig.android.R
import com.taig.android.util.Companion
import com.taig.android.widget.Widget

import scala.language.reflectiveCalls

/**
 * View extension that allows to apply a radius
 */
trait	Radius
extends	View
with	Widget
{
	private val radius = new
	{
		var enabled = true

		var value = 0f
	}

	private val rectangle = new RectF( )

	private val paint = new
	{
		val image = new Paint{ setXfermode( new PorterDuffXfermode( Mode.SRC_IN ) ) }

		val shape = new Paint( Paint.ANTI_ALIAS_FLAG ){ setXfermode( new PorterDuffXfermode( Mode.SRC ) ) }
	}

	initialize( R.styleable.Widget_Image_Radius, ( array: TypedArray ) =>
	{
		setRadiusEnabled( array.getBoolean( R.styleable.Widget_Image_Radius_radius, isRadiusEnabled ) )

		setRadius( array.getDimension( R.styleable.Widget_Image_Radius_radius, getRadius ) )
	} )

	/**
	 * Check whether radius drawing is enabled and therefore being applied to the view
	 *
	 * @return <code>true</code> (default) if radius drawing is enabled, <code>false</code> otherwise
	 * @see [[R.styleable.Widget_Image_Radius_radius]]
	 */
	def isRadiusEnabled = radius.enabled

	/**
	 * Enable or disable radius drawing
	 *
	 * @param enabled <code>true</code> (default) to enable, <code>false</code> disable
	 * @see [[R.styleable.Widget_Image_Radius_radius]]
	 */
	def setRadiusEnabled( enabled: Boolean ) = radius.enabled = enabled

	/**
	 * Get the radius
	 *
	 * @return The current radius (default: <code>0</code>)
	 * @see [[R.styleable.Widget_Image_Radius_radius]]
	 */
	def getRadius = radius.value

	/**
	 * Set the radius
	 *
	 * @param value The radius (default: 0)
	 * @see [[R.styleable.Widget_Image_Radius_radius]]
	 */
	def setRadius( value: Float ) = radius.value = value

	override def draw( canvas: Canvas ) =
	{
		if( isRadiusEnabled )
		{
			rectangle.set( 0, 0, canvas.getWidth, canvas.getHeight )
			val restore = canvas.saveLayer( rectangle, null, Canvas.ALL_SAVE_FLAG )
			canvas.drawRoundRect( rectangle, getRadius, getRadius, paint.shape )
			canvas.saveLayer( rectangle, paint.image, Canvas.ALL_SAVE_FLAG )
			super.draw( canvas )
			canvas.restoreToCount( restore )
		}
		else
		{
			super.onDraw( canvas )
		}
	}
}

object	Radius
extends	Companion