package com.taig.android.widget.image

import android.graphics.PorterDuff.Mode
import android.graphics._
import com.taig.android.R
import com.taig.android.widget.Image

/**
 * ImageView extension that allows to apply a radius to the src drawable
 */
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

	{
		val array = context.obtainStyledAttributes( attributes, R.styleable.Widget_Image_Radius )

		setRadiusEnabled( array.getBoolean( R.styleable.Widget_Image_Radius_radius, isRadiusEnabled ) )

		setRadius( array.getDimension( R.styleable.Widget_Image_Radius_radius, getRadius ) )

		array.recycle()
	}

	def isRadiusEnabled = radius.enabled

	def setRadiusEnabled( enabled: Boolean ) = radius.enabled = enabled

	def getRadius = radius.value

	def setRadius( value: Float ) = radius.value = value

	override def onDraw( canvas: Canvas )
	{
		val drawable = getDrawable

		if( drawable == null || !isRadiusEnabled )
		{
			super.onDraw( canvas )
			return
		}

		rectangle.set( drawable.getBounds )

		getImageMatrix.mapRect( rectangle )
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

	override def draw( canvas: Canvas ) = super.draw( canvas )
}

object Radius
{
	val Tag = getClass.getName
}