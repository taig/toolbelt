package com.taig.android.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import com.taig.android._
import com.taig.android.conversion._

class	EditText( context: Context, attributes: AttributeSet, style: Int )
extends	android.widget.EditText( context, attributes, style )
with	Validatable
{
	def this( context: Context, attributes: AttributeSet ) = this( context, attributes, android.R.attr.editTextStyle )

	def this( context: Context ) = this( context, null )

	private val array = context.obtainStyledAttributes( attributes, R.styleable.Widget_Validation, style, 0 )

	var regex = Option( array.getString( R.styleable.Widget_Validation_regex ) )

	var error = Option( array.getString( R.styleable.Widget_Validation_error ) )

	array.recycle()

	setOnFocusChangeListener( ( _: View, focus: Boolean ) => if( !focus ) validate() )

	override def isValid = regex.map( getText.toString.matches ).getOrElse( true )

	override def validate() = if( !isValid )
	{
		setError( error.getOrElse( context.getString( R.string.validation_error ) ) )
	}
}