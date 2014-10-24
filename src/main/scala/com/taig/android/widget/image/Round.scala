package com.taig.android.widget.image

trait Round extends AspectRatio with Radius
{
	ratio.setValue( 1 )

	override def onMeasure( widthMeasure: Int, heightMeasure: Int )
	{
		super.onMeasure( widthMeasure, heightMeasure )

		radius.setValue( getMeasuredWidth / 2f )
	}
}