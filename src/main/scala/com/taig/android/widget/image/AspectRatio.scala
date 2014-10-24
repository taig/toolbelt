package com.taig.android.widget.image

import com.taig.android.R
import com.taig.android.widget.Image

trait AspectRatio extends Image
{
	val ratio = new
	{
		private var dominance = 0

		def getDominance = dominance

		def setDominance( dominance: Int ) { this.dominance = dominance }

		private var enabled = true

		def isEnabled = enabled

		def setEnabled( enabled: Boolean ) { this.enabled = enabled }

		private var value = 1f

		def getValue = value

		def setValue( value: Float ) { this.value = value }
	}

	{
		val array = context.obtainStyledAttributes( attributes, R.styleable.Widget_Image_AspectRatio )

		ratio.setDominance( array.getInt( R.styleable.Widget_Image_AspectRatio_dominance, ratio.getDominance ) )

		ratio.setEnabled( array.getBoolean( R.styleable.Widget_Image_AspectRatio_enabled, ratio.isEnabled ) )

		ratio.setValue( array.getFloat( R.styleable.Widget_Image_AspectRatio_ratio, ratio.getValue ) )

		array.recycle()
	}

	override def onMeasure( widthMeasure: Int, heightMeasure: Int )
	{
		super.onMeasure( widthMeasure, heightMeasure )

		if( ratio.isEnabled )
		{
			val ( width, height ) = ratio.getDominance match
			{
				case 0 => ( getMeasuredWidth, ( getMeasuredWidth * ratio.getValue ).toInt )
				case 1 => ( ( getMeasuredHeight * ratio.getValue ).toInt, getMeasuredHeight )
			}

			setMeasuredDimensions( width, height )
		}
	}
}