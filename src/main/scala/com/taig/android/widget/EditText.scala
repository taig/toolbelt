package com.taig.android.widget

import android.content.Context
import android.util.{AttributeSet, Patterns}
import android.view.View
import com.taig.android._
import com.taig.android.conversion._

import scala.collection.mutable

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

	var regex =
	{
		val all = mutable.ArrayBuffer.empty[String]

		Option( Seq( array.getString( R.styleable.Widget_Validation_regex ) ) ).foreach( all.append )

		val rules = array.getInt( R.styleable.Widget_Validation_rules, Validatable.Flag.None )

		if( ( rules & Validatable.Flag.None ) != Validatable.Flag.None )
		{
			if( ( rules & Validatable.Flag.Alpha ) == Validatable.Flag.Alpha ) all.append( "[\\p{L}]*" )
			if( ( rules & Validatable.Flag.AlphaDash ) == Validatable.Flag.AlphaDash ) all.append( "[\\p{L}\\-]*" )
			if( ( rules & Validatable.Flag.AlphaNumeric ) == Validatable.Flag.AlphaNumeric ) all.append( "[\\p{L}0-9]*" )
			if( ( rules & Validatable.Flag.Email ) == Validatable.Flag.Email ) all.append( Patterns.EMAIL_ADDRESS.pattern() )
			if( ( rules & Validatable.Flag.Integer ) == Validatable.Flag.Integer ) all.append( "[0-9]*" )
			if( ( rules & Validatable.Flag.Numeric ) == Validatable.Flag.Numeric ) all.append( "\\d*([\\.,]\\d+)?" )
			if( ( rules & Validatable.Flag.Required ) == Validatable.Flag.Required ) all.append( ".+" )
		}

		all
	}

	array.recycle()

	require( !( matches.isDefined && regex.nonEmpty ), "Can't define regex and matches field, choose one" )

	setOnFocusChangeListener( ( _: View, focus: Boolean ) => if( !focus ) validate() )

	override def isValid = if( regex.nonEmpty )
	{
		regex.forall( getText.toString.matches )
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