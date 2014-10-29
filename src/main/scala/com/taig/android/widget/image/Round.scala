package com.taig.android.widget.image

trait Round extends AspectRatio with Radius
{
	setRatio( 1 )

	override def onMeasure( widthMeasure: Int, heightMeasure: Int )
	{
		super.onMeasure( widthMeasure, heightMeasure )

		setRadius( getMeasuredWidth / 2f )
	}
}