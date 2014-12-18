package com.taig.android.widget.image

/**
 * ImageView extension that draws its src drawable in a circle
 * 
 * This is achieved by using a [[AspectRatio]] of 1, and a [[Radius]] as big as half a dimension.
 */
trait	Round
extends	AspectRatio
with	Radius
{
	setRatio( 1 )

	override def onMeasure( widthMeasure: Int, heightMeasure: Int )
	{
		super.onMeasure( widthMeasure, heightMeasure )

		setRadius( getMeasuredWidth / 2f )
	}
}