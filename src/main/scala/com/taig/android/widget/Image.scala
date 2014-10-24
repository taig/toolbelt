package com.taig.android.widget

import android.content.Context
import android.graphics.{Matrix, Canvas, Bitmap, Picture}
import android.graphics.drawable.PictureDrawable
import android.util.{AttributeSet, TypedValue}
import android.view.View
import android.widget.ImageView
import com.larvalabs.svgandroid.SVGBuilder
import com.taig.android.R

/**
 * An enhanced ImageView that adds support for SVG assets
 * 
 * SVGs have to be stored in the res/raw folder and can be referenced with the src attribute.
 */
class Image( val context: Context, val attributes: AttributeSet, val style: Int ) extends ImageView( context, attributes, style )
{
	def this( context: Context, attributes: AttributeSet ) = this( context, attributes, 0 )

	def this( context: Context ) = this( context, null )

	{
		val array = context.obtainStyledAttributes( attributes, R.styleable.Widget_Image )

		val source = array.getResourceId( R.styleable.Widget_Image_android_src, -1 )

		if( source > -1 )
		{
			setImageResource( source )
		}

		array.recycle()
	}

	/**
	 * A minor hack to make [[setMeasuredDimension()]] available to public
	 */
	def setMeasuredDimensions( width: Int, height: Int ) = super.setMeasuredDimension( width, height )

	override def setImageResource( resId: Int )
	{
		val value = new TypedValue()
		context.getResources.getValue( resId, value, true )

		if( value.string.toString.endsWith( "svg" ) )
		{
			// Make sure hardware rendering is disabled
			setLayerType( View.LAYER_TYPE_SOFTWARE, null )
			setImageDrawable( new SVGBuilder().readFromResource( getResources, resId ).build().getDrawable )
		}
		else
		{
			super.setImageResource( resId )
		}
	}
}