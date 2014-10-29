package com.taig.android.widget.image

import android.graphics.PorterDuff.Mode
import android.graphics._
import com.taig.android.R
import com.taig.android.widget.Image

trait Radius extends Image
{
	val radius = new
	{
		val paint = new Paint( Paint.ANTI_ALIAS_FLAG )

		private var value = 0f

		def getValue = value

		def setValue( value: Float ) { this.value = value }
	}

	private val rectangle = new RectF()

	private val paint1 = new Paint( Paint.ANTI_ALIAS_FLAG )
	
	private val paint2 = new Paint{ setXfermode( new PorterDuffXfermode( Mode.SRC_IN ) ) }

	{
		val array = context.obtainStyledAttributes( attributes, R.styleable.Widget_Image_Radius )

		radius.setValue( array.getDimension( R.styleable.Widget_Image_Radius_radius, radius.getValue ) )

		array.recycle()
	}

	override def onDraw( canvas: Canvas )
	{
		val drawable = getDrawable

		if( drawable == null )
		{
			super.onDraw( canvas )
			return
		}

		rectangle.set( drawable.getBounds )

		getImageMatrix.mapRect( rectangle )
		rectangle.offset( getPaddingLeft, getPaddingTop )

		// Prevent radius being drawn out of canvas bounds
		rectangle.intersect( new RectF( 0, 0, canvas.getWidth, canvas.getHeight ) )

		val restore = canvas.saveLayer( rectangle, null, Canvas.ALL_SAVE_FLAG )
		canvas.drawRoundRect( rectangle, radius.getValue, radius.getValue, paint1 )
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