package io.taig.android.widget.image

import android.graphics.drawable.PictureDrawable
import android.util.{ AttributeSet, TypedValue }
import android.view.View.LAYER_TYPE_SOFTWARE
import android.widget.ImageView
import com.caverock.androidsvg.SVG
import io.taig.android.R

/**
 * An ImageView extension that adds support for SVG assets
 *
 * SVGs have to be stored in the res/raw folder and can be referenced with the src attribute.
 */
trait Svg
        extends ImageView {
    def attributes: AttributeSet

    {
        val array = getContext.obtainStyledAttributes( this.attributes, R.styleable.Widget_Image )

        val source = array.getResourceId( R.styleable.Widget_Image_android_src, -1 )

        if ( source > -1 ) {
            setImageResource( source )
        }

        array.recycle()
    }

    override def setImageResource( resId: Int ) {
        val value = new TypedValue()
        getContext.getResources.getValue( resId, value, true )

        if ( value.string.toString.endsWith( "svg" ) ) {
            // Make sure hardware rendering is disabled
            setLayerType( LAYER_TYPE_SOFTWARE, null )
            setImageDrawable( new PictureDrawable( SVG.getFromResource( getResources, resId ).renderToPicture() ) )
        }
        else {
            super.setImageResource( resId )
        }
    }
}