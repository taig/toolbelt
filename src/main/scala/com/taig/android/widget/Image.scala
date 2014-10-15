package com.taig.android.widget

import android.content.Context
import android.util.{AttributeSet, TypedValue}
import android.view.View
import android.widget.ImageView
import com.larvalabs.svgandroid.SVGBuilder

/**
 * An enhanced ImageView that adds support for SVG assets
 * 
 * SVGs have to be stored in the res/raw folder and can be referenced with the src attribute.
 */
class Image( context: Context, attributes: AttributeSet, style: Int ) extends ImageView( context, attributes, style )
{
	def this( context: Context, attributes: AttributeSet ) = this( context, attributes, 0 )

	def this( context: Context ) = this( context, null )

	val styleable = Class.forName( "android.R$styleable" )

	val array = context.obtainStyledAttributes(
		attributes,
		styleable.getField( "ImageView" ).get( styleable ).asInstanceOf[Array[Int]],
		style,
		0
	)

	val src = array.getResourceId( styleable.getField( "ImageView_src" ).getInt( styleable ), 0 )

	if( context.getResources.getResourceTypeName( src ) == "raw" )
	{
		val value = new TypedValue()
		context.getResources.getValue( src, value, true )

		if( value.string.toString.endsWith( "svg" ) )
		{
			setImageResource( src )
		}
	}

	array.recycle()

	override def setImageResource( resId: Int )
	{
		if( context.getResources.getResourceTypeName( resId ) == "raw" )
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