package com.taig.android.widget.view

import android.content.res.TypedArray
import android.view.View
import android.view.View.MeasureSpec
import android.view.View.MeasureSpec._
import com.taig.android.R
import com.taig.android.util.Companion
import com.taig.android.widget.Widget
import com.taig.android.widget.view.AspectRatio.Dominance

/**
 * ImageView extension that allows to specify fixed aspect ratios to the view
 * 
 * @see [[R.styleable.Widget_Image_AspectRatio]]
 */
trait	AspectRatio
extends	View
with	Widget
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

	override def onMeasure( widthMeasure: Int, heightMeasure: Int )
	{
		def resolve( dominance: Int = getRatioDominance ): Unit =
		{
			val ( width, height ) = ( getMeasuredWidth, getMeasuredHeight )

			dominance match
			{
				case Dominance.Width => setMeasuredDimensionReflective( width, ( width * getRatio ).toInt )
				case Dominance.Height => setMeasuredDimensionReflective( ( height * getRatio ).toInt, height )
				case Dominance.Auto => ( getMode( widthMeasure ), getMode( heightMeasure ) ) match
				{
					case ( widthMode, EXACTLY ) if widthMode != EXACTLY => resolve( Dominance.Height )
					case _ => resolve( Dominance.Width )
				}
			}
		}

		super.onMeasure( widthMeasure, heightMeasure )

		if( isRatioEnabled )
		{
			resolve()
		}
	}
}

object	AspectRatio
extends	Companion
{
	val Dominance = new
	{
		val Auto = 0

		val Width = 1

		val Height = 2
	}
}