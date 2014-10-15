package com.taig.android.widget

import android.content.Context
import android.util.{AttributeSet, Patterns}
import android.view.View
import android.widget.TextView
import com.taig.android._
import com.taig.android.conversion._
import com.taig.android.widget.Validatable.Flag

class	EditText( context: Context, attributes: AttributeSet, style: Int )
extends	android.widget.EditText( context, attributes, style )
with	Validatable
{
	def this( context: Context, attributes: AttributeSet ) = this( context, attributes, android.R.attr.editTextStyle )

	def this( context: Context ) = this( context, null )

	private val array = context.obtainStyledAttributes( attributes, R.styleable.Widget_Validation, style, 0 )

	val validation = new
	{
		var message = Option( array.getString( R.styleable.Widget_Validation_message ) )

		var icon = Option( array.getDrawable( R.styleable.Widget_Validation_icon ) )

		var `match` = array.getResourceId( R.styleable.Widget_Validation_matches, -1 ) match
		{
			case -1 => None
			case id => Some( id )
		}

		var regex = Option( array.getString( R.styleable.Widget_Validation_regex ) )

		var required = array.getBoolean( R.styleable.Widget_Validation_required, false )

		var rule = array.getInt( R.styleable.Widget_Validation_rule, -1 ) match
		{
			case -1 => None
			case id => Some( id )
		}
	}

	array.recycle()

	setOnFocusChangeListener( ( _: View, focus: Boolean ) => if( !focus ) validate(): Unit )

	override def isValid =
	{
		val regex = validation.regex ++ validation.rule.map
		{
			case Flag.Alpha => "[\\p{L}]*"
			case Flag.AlphaDash => "[\\p{L}\\-]*"
			case Flag.AlphaNumeric => "[\\p{L}0-9]*"
			case Flag.Email => Patterns.EMAIL_ADDRESS.pattern()
			case Flag.Integer => "[0-9]*"
			case Flag.Numeric => "\\d*([\\.,]\\d+)?"
			case Flag.Phone => "(\\+?\\d+)?"
		}

		val `match` = validation.`match`
			.map( getRootView.findViewById )
			.collect{ case text: TextView => text.getText.toString }

		regex.forall( getText.toString.matches ) && `match`.forall( _ == getText.toString )
	}

	override def validate() = if( isValid )
	{
		setError( null, null )
		true
	}
	else
	{
		val message = validation.message.getOrElse( getResources.getString( R.string.validation_error ) )

		validation.icon match
		{
			case Some( icon ) => setError( message, icon )
			case None => setError( message )
		}

		false
	}
}