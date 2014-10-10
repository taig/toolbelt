package com.taig.android.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import com.larvalabs.svgandroid.SVGBuilder

class SVG( context: Context, attributes: AttributeSet, style: Int ) extends ImageView( context, attributes, style )
{
	def this( context: Context, attributes: AttributeSet ) = this( context, attributes, 0 )

	def this( context: Context ) = this( context, null )

	// Make sure hardware rendering is disabled
	setLayerType( View.LAYER_TYPE_SOFTWARE, null )

	val styleable = Class.forName( "android.R$styleable" )

	val array = context.obtainStyledAttributes(
		attributes,
		styleable.getField( "ImageView" ).get( styleable ).asInstanceOf[Array[Int]],
		style,
		0
	)

	val src = array.getResourceId( styleable.getField( "ImageView_src" ).getInt( styleable ), 0 )

	if( src != 0 )
	{
		setSVG( src )
	}

	def setSVG( rawId: Int ) = setImageDrawable
	{
		new SVGBuilder().readFromResource( getResources, rawId ).build().getDrawable
	}
}