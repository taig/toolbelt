package com.taig.android.widget.image

import android.view.View.MeasureSpec._
import com.taig.android.R
import com.taig.android.widget.Image
import com.taig.android.widget.image.AspectRatio._

trait AspectRatio extends Image
{
	private val ratio = new
	{
		var dominance = Dominance.Auto

		var enabled = true

		var value = 1f
	}

	{
		val array = context.obtainStyledAttributes( attributes, R.styleable.Widget_Image_AspectRatio )

		setRatioDiminance( array.getInt( R.styleable.Widget_Image_AspectRatio_dominance, getRatioDominance ) )

		setRatioEnabled( array.getBoolean( R.styleable.Widget_Image_AspectRatio_enabled, isRatioEnabled ) )

		setRatio( array.getFloat( R.styleable.Widget_Image_AspectRatio_ratio, getRatio ) )

		array.recycle()
	}

	def getRatioDominance = ratio.dominance

	def setRatioDiminance( dominance: Int ) = ratio.dominance = dominance

	def isRatioEnabled = ratio.enabled

	def setRatioEnabled( enabled: Boolean ) = ratio.enabled = enabled

	def getRatio = ratio.value

	def setRatio( value: Float ) = ratio.value = value

	override def onMeasure( widthMeasure: Int, heightMeasure: Int )
	{
		( Option( getDrawable ), ( getMode( widthMeasure ), getMode( heightMeasure ) ) ) match
		{
			case ( None, _ ) => setMeasuredDimensions( 0, 0 )
			case ( _, ( EXACTLY, EXACTLY ) ) =>
			{
				val ( width, height ) = getRatioDominance match
				{
					case 0 => ( getSize( widthMeasure ), ( getSize( widthMeasure ) * getRatio ).toInt )
					case 1 => ( ( getSize( heightMeasure ) * getRatio ).toInt, getSize( heightMeasure ) )
				}

				setMeasuredDimensions( width, height )

				if( getAdjustViewBounds )
				{
					getLayoutParams.width = width
					getLayoutParams.height = height
				}
			}
			case ( _, ( EXACTLY, _ ) ) =>
			{
				val width = getSize( widthMeasure )
				setMeasuredDimensions( ( width * getRatio ).toInt, width )
			}
			case ( _, ( _, EXACTLY ) ) =>
			{
				val height = getSize( heightMeasure )
				setMeasuredDimensions( ( height * getRatio ).toInt, height )
			}
			case ( Some( drawable ), ( UNSPECIFIED, UNSPECIFIED ) ) =>
			{
				val ( width, height ) = getRatioDominance match
				{
					case 0 => ( drawable.getIntrinsicWidth, ( drawable.getIntrinsicWidth * getRatio ).toInt )
					case 1 => ( ( drawable.getIntrinsicHeight * getRatio ).toInt, drawable.getIntrinsicHeight )
				}

				setMeasuredDimensions( width, height )
			}
			case ( Some( drawable ), ( UNSPECIFIED, _ ) ) =>
			{
				setMeasuredDimensions(
					( drawable.getIntrinsicWidth * getRatio ).toInt,
					drawable.getIntrinsicWidth
				)
			}
			case ( Some( drawable ), ( _, UNSPECIFIED ) ) =>
			{
				setMeasuredDimensions(
					( drawable.getIntrinsicHeight * getRatio ).toInt,
					drawable.getIntrinsicHeight
				)
			}
			case _ => super.onMeasure( widthMeasure, heightMeasure )
		}
	}
}

object AspectRatio
{
	val Dominance = new
	{
		val Auto = 0

		val Width = 1

		val Height = 2
	}
}