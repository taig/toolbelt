package com.taig.android.widget.text

import android.content.res.TypedArray
import android.graphics.Typeface
import android.text.Editable
import com.taig.android.R
import com.taig.android.text.Watcher
import com.taig.android.widget.Text

trait	HintStyle
extends	Text
{
	private val hint = new
	{
		var style = -1
	}

	initialize( R.styleable.Widget_Text_HintStyle, ( array: TypedArray ) =>
	{
		hint.style = array.getInt( R.styleable.Widget_Text_HintStyle_hintTextStyle, hint.style )
	} )

	private var textStyle = () => Option( getTypeface ).map( _.getStyle ).getOrElse( -1 )

	addTextChangedListener( new Watcher
	{
		override def afterTextChanged( text: Editable )
		{
			setTypeface( null, if( text.length() > 0 ) textStyle() else hint.style )
		}
	} )

	def getHintTextStyle = hint.style

	def setHintTextStyle( style: Int ) = hint.style = style

	override def setTypeface( typeface: Typeface, style: Int )
	{
		textStyle = () => style
		super.setTypeface( typeface, style )
	}
}