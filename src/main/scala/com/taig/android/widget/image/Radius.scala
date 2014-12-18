package com.taig.android.widget.image

import android.content.res.TypedArray
import android.graphics.PorterDuff.Mode
import android.graphics._
import com.taig.android.R
import com.taig.android.widget.Image

/**
 * ImageView extension that allows to apply a radius to the src drawable
 */
// TODO Allow background drawing with radius
// TODO Solid colors probably don't work?
trait Radius extends Image
{
	private val radius = new
	{
		var enabled = true

		var value = 0f
	}

	private val rectangle = new RectF()

	private val intersection = new RectF( 0, 0, 0, 0 )

	private val paint1 = new Paint( Paint.ANTI_ALIAS_FLAG )

	private val paint2 = new Paint{ setXfermode( new PorterDuffXfermode( Mode.SRC_IN ) ) }

	initialize( R.styleable.Widget_Image_Radius, ( array: TypedArray ) =>
	{
		setRadiusEnabled( array.getBoolean( R.styleable.Widget_Image_Radius_radius, isRadiusEnabled ) )

		setRadius( array.getDimension( R.styleable.Widget_Image_Radius_radius, getRadius ) )
	} )

	/**
	 * Check whether radius drawing is enabled and therefore being applied to the drawable
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

	override def onDraw( canvas: Canvas )
	{
		val drawable = getDrawable

		if( drawable == null || !isRadiusEnabled )
		{
			super.onDraw( canvas )
			return
		}

		rectangle.set( 0, 0, drawable.getIntrinsicWidth, drawable.getIntrinsicHeight )

		rectangle.offset( getPaddingLeft, getPaddingTop )

		// Prevent radius being drawn out of canvas bounds
		intersection.right = canvas.getWidth
		intersection.bottom = canvas.getHeight
		rectangle.intersect( intersection )

		val restore = canvas.saveLayer( rectangle, null, Canvas.ALL_SAVE_FLAG )
		canvas.drawRoundRect( rectangle, getRadius, getRadius, paint1 )
		canvas.saveLayer( rectangle, paint2, Canvas.ALL_SAVE_FLAG )
		super.onDraw( canvas )
		canvas.restoreToCount( restore )
	}
}

object Radius
{
	val Tag = getClass.getName
}