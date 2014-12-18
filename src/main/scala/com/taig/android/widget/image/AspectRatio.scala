package com.taig.android.widget.image

import android.content.res.TypedArray
import android.view.View.MeasureSpec._
import com.taig.android.R
import com.taig.android.widget.Image
import com.taig.android.widget.image.AspectRatio._

/**
 * ImageView extension that allows to specify fixed aspect ratios to the view
 * 
 * @see [[R.styleable.Widget_Image_AspectRatio]]
 */
trait	AspectRatio
extends	Image
{
	private val ratio = new
	{
		var dominance = Dominance.Auto

		var enabled = true

		var value = 1f
	}

	initialize( R.styleable.Widget_Image_AspectRatio, ( array: TypedArray ) =>
	{
		setRatioDominance( array.getInt( R.styleable.Widget_Image_AspectRatio_dominance, getRatioDominance ) )

		setRatioEnabled( array.getBoolean( R.styleable.Widget_Image_AspectRatio_ratio, isRatioEnabled ) )

		setRatio( array.getFloat( R.styleable.Widget_Image_AspectRatio_ratio, getRatio ) )
	} )

	/**
	 * Get the dominant dimension
	 * 
	 * The dominant dimension is the axis, that will not be scaled. If [[Dominance.Width]] is dominant, then the view's
	 * height will be the width multiplied with the aspect ratio.
	 * 
	 * If the dominance is set to [[Dominance.Auto]] then the smaller dimension will be used as the dominant
	 * one.
	 * 
	 * @return The dominant dimension: [[Dominance.Auto]] (default), [[Dominance.Width]] or [[Dominance.Height]]
	 * @see [[R.styleable.Widget_Image_AspectRatio_dominance]]
	 */
	def getRatioDominance = ratio.dominance

	/**
	 * Set the dominant dimension
	 * 
	 * @param dominance [[Dominance.Auto]] (default), [[Dominance.Width]] or [[Dominance.Height]]
	 * @see [[R.styleable.Widget_Image_AspectRatio_dominance]]
	 */
	def setRatioDominance( dominance: Int ) = ratio.dominance = dominance

	/**
	 * Check whether the ratio constrains are enabled and therefore being applied to the view
	 * 
	 * @return <code>true</code> (default) if the ratio constrains are enabled, <code>false</code> otherwise
	 * @see [[R.styleable.Widget_Image_AspectRatio_ratio]]
	 */
	def isRatioEnabled = ratio.enabled

	/**
	 * Enable or disable the ratio constraints
	 * 
	 * @param enabled <code>true</code> (default) to enable, <code>false</code> disable
	 * @see [[R.styleable.Widget_Image_AspectRatio_ratio]]
	 */
	def setRatioEnabled( enabled: Boolean ) = ratio.enabled = enabled

	/**
	 * Get the aspect ratio
	 * 
	 * @return The current aspect ratio (default: <code>1</code>)
	 * @see [[R.styleable.Widget_Image_AspectRatio_ratio]]
	 */
	def getRatio = ratio.value

	/**
	 * Set the aspect ratio
	 * 
	 * @param value The aspect ratio (default: 1)
	 * @see [[R.styleable.Widget_Image_AspectRatio_ratio]]
	 */
	def setRatio( value: Float ) = ratio.value = value

	/**
	 * Apply the aspect ratio to a given dimension, respecting the dominant dimension
	 * 
	 * @param width Dimension width
	 * @param height Dimension height
	 * @param dominance Dominant dimension (default: [[getRatioDominance]])
	 * @return The resolution with the aspect ratio applied
	 */
	private def resolve( width: Int, height: Int, dominance: Int = getRatioDominance ): ( Int, Int ) = dominance match
	{
		case Dominance.Auto if width <= height => resolve( width, height, Dominance.Width )
		case Dominance.Auto if height < width => resolve( width, height, Dominance.Height )
		case Dominance.Width => ( width, ( width * getRatio ).toInt )
		case Dominance.Height => ( ( height * getRatio ).toInt, height )
	}

	override def onMeasure( widthMeasure: Int, heightMeasure: Int )
	{
		val drawable = getDrawable

		if( drawable == null || !isRatioEnabled )
		{
			super.onMeasure( widthMeasure, heightMeasure )
			return
		}

		( getMode( widthMeasure ), getMode( heightMeasure ) ) match
		{
			case ( EXACTLY, EXACTLY ) =>
			{
				val ( width, height ) = resolve( getSize( widthMeasure ), getSize( heightMeasure ) )

				setMeasuredDimensions( width, height )

				if( getAdjustViewBounds )
				{
					getLayoutParams.width = width
					getLayoutParams.height = height
				}
			}
			case ( EXACTLY, _ ) =>
			{
				val width = getSize( widthMeasure )
				setMeasuredDimensions( ( width * getRatio ).toInt, width )
			}
			case ( _, EXACTLY ) =>
			{
				val height = getSize( heightMeasure )
				setMeasuredDimensions( ( height * getRatio ).toInt, height )
			}
			case ( UNSPECIFIED, UNSPECIFIED ) =>
			{
				val ( width, height ) = resolve( drawable.getIntrinsicWidth, drawable.getIntrinsicHeight )
				setMeasuredDimensions( width, height )
			}
			case ( UNSPECIFIED, _ ) =>
			{
				setMeasuredDimensions( ( drawable.getIntrinsicWidth * getRatio ).toInt, drawable.getIntrinsicWidth )
			}
			case ( _, UNSPECIFIED ) =>
			{
				setMeasuredDimensions( ( drawable.getIntrinsicHeight * getRatio ).toInt, drawable.getIntrinsicHeight )
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