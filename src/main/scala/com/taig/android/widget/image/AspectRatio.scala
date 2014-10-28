package com.taig.android.widget.image

import android.view.View.MeasureSpec._
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
		( Option( getDrawable ), ( getMode( widthMeasure ), getMode( heightMeasure ) ) ) match
		{
			case ( None, _ ) => setMeasuredDimensions( 0, 0 )
			case ( _, ( EXACTLY, EXACTLY ) ) =>
			{
				val ( width, height ) = ratio.getDominance match
				{
					case 0 => ( getSize( widthMeasure ), ( getSize( widthMeasure ) * ratio.getValue ).toInt )
					case 1 => ( ( getSize( heightMeasure ) * ratio.getValue ).toInt, getSize( heightMeasure ) )
				}

				setMeasuredDimensions( width, height )

				if( getAdjustViewBounds )
				{
					getLayoutParams.width = width
					getLayoutParams.height = height
				}
			}
			case ( _, ( _, EXACTLY ) ) =>
			{
				val height = getSize( heightMeasure )
				setMeasuredDimensions( ( height * ratio.getValue ).toInt, height )
			}
			case ( _, ( EXACTLY, _ ) ) =>
			{
				val width = getSize( widthMeasure )
				setMeasuredDimensions( ( width * ratio.getValue ).toInt, width )
			}
			case ( Some( drawable ), ( UNSPECIFIED, UNSPECIFIED ) ) =>
			{
				val ( width, height ) = ratio.getDominance match
				{
					case 0 => ( drawable.getIntrinsicWidth, ( drawable.getIntrinsicWidth * ratio.getValue ).toInt )
					case 1 => ( ( drawable.getIntrinsicHeight * ratio.getValue ).toInt, drawable.getIntrinsicHeight )
				}

				setMeasuredDimensions( width, height )
			}
			case ( Some( drawable ), ( UNSPECIFIED, _ ) ) =>
			{
				setMeasuredDimensions(
					( drawable.getIntrinsicWidth * ratio.getValue ).toInt,
					drawable.getIntrinsicWidth
				)
			}
			case ( Some( drawable ), ( _, UNSPECIFIED ) ) =>
			{
				setMeasuredDimensions(
					( drawable.getIntrinsicHeight * ratio.getValue ).toInt,
					drawable.getIntrinsicHeight
				)
			}
			case _ => super.onMeasure( widthMeasure, heightMeasure )
		}
	}
}