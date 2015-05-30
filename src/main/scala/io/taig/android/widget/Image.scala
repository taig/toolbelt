package io.taig.android.widget

import android.content.Context
import android.content.res.TypedArray
import android.graphics.drawable.PictureDrawable
import android.util.{AttributeSet, TypedValue}
import android.view.View
import android.widget.ImageView
import com.caverock.androidsvg.SVG
import io.taig.android.R

/**
 * An enhanced ImageView that adds support for SVG assets
 * 
 * SVGs have to be stored in the res/raw folder and can be referenced with the src attribute.
 */
class	Image( val attributes: AttributeSet = null, val style: Int = 0 )( implicit context: Context )
extends	ImageView( context, attributes, style )
with	Widget.Styleable
{
	def this( context: Context, attributes: AttributeSet, style: Int ) = this( attributes, style )( context )

	def this( context: Context, attributes: AttributeSet ) = this( attributes )( context )

	def this( context: Context ) = this()( context )

	initialize( R.styleable.Widget_Image, ( array: TypedArray ) =>
	{
		val source = array.getResourceId( R.styleable.Widget_Image_android_src, -1 )

		if( source > -1 )
		{
			setImageResource( source )
		}
	} )

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
			setImageDrawable( new PictureDrawable( SVG.getFromResource( getResources, resId ).renderToPicture() ) )
		}
		else
		{
			super.setImageResource( resId )
		}
	}
}