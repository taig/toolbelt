package com.taig.android.widget

import android.content.Context
import android.util.AttributeSet
import android.widget.{TextView, EditText, ViewSwitcher}
import com.taig.android.graphic.Color

/**
 * A widget that can switch between an [[android.widget.EditText]] and a [[android.widget.TextView]]
 * 
 * By default it displays the TextView
 */
class InlineEditText( context: Context, attributes: AttributeSet ) extends ViewSwitcher( context, attributes )
{
	def this( context: Context ) = this( context, null )

	val edit = new EditText( context, attributes )

	val text = new TextView( context, attributes, android.R.attr.editTextStyle )

	text.setBackgroundColor( Color.Transparent )
	text.setInputType( 0 )

	addView( text )
	addView( edit )

	def showText() = setDisplayedChild( 0 )

	def showEdit() = setDisplayedChild( 1 )

	def getText = edit.getText

	def setText( text: CharSequence )
	{
		this.edit.setText( text )
		this.text.setText( text )
	}

	def setText( resId: Int )
	{
		this.edit.setText( resId )
		this.text.setText( resId )
	}

	override def setDisplayedChild( whichChild: Int )
	{
		if( whichChild == 0 )
		{
			text.setText( getText )
		}

		super.setDisplayedChild( whichChild )
	}
}