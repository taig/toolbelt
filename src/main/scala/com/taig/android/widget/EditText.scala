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

	var error = Option( array.getString( R.styleable.Widget_Validation_error ) )

	var matches = array.getResourceId( R.styleable.Widget_Validation_matches, -1 ) match
	{
		case -1 => None
		case id => Some( id )
	}

	var regex = Option( array.getString( R.styleable.Widget_Validation_regex ) )

	array.recycle()

	require( Seq( matches, regex ).count( _.isDefined ) < 2, "Can't define regex and matches field, choose one" )

	setOnFocusChangeListener( ( _: View, focus: Boolean ) => if( !focus ) validate() )

	override def isValid = if( regex.isDefined )
	{
		regex.map( getText.toString.matches ).get
	}
	else if( matches.isDefined )
	{
		val target = getRootView.findViewById( matches.get ).asInstanceOf[android.widget.EditText].getText.toString
		target == getText.toString
	}
	else
	{
		true
	}

	override def validate() = if( !isValid )
	{
		setError( error.getOrElse( context.getString( R.string.validation_error ) ) )
	}
}