package com.taig.android.widget

import android.content.Context
import android.util.{AttributeSet, TypedValue}
import android.view.View
import android.widget.TextView
import com.taig.android._
import com.taig.android.conversion._
import com.taig.android.widget.validation.Type._
import com.taig.android.widget.validation.Validatable

class	EditText( context: Context, attributes: AttributeSet, style: Int )
extends	android.widget.EditText( context, attributes, style )
with	Validatable
{
	def this( context: Context, attributes: AttributeSet ) = this( context, attributes, android.R.attr.editTextStyle )

	def this( context: Context ) = this( context, null )

	val validation = new
	{
		private val array = context.obtainStyledAttributes( attributes, R.styleable.Widget_Validation, style, 0 )

		private val resolve = new
		{
			private val value = new TypedValue

			def enabled( index: Int ) = if( array.getValue( index, value ) )
			{
				value.`type` match
				{
					case TypedValue.TYPE_INT_BOOLEAN if value.resourceId == 0 => array.getBoolean( index, false )
					case _ => true
				}
			}
			else
			{
				false
			}

			def message( index: Int, resource: Int ) = Option( array.getString( index ) ).getOrElse
			{
				// Check if the main index received a string, otherwise fall back to default resource
				if( value.`type` == TypedValue.TYPE_STRING ) value.string else context.getString( resource )
			}
		}

		val icon = Option( array.getDrawable( R.styleable.Widget_Validation_icon ) )

		val alpha = new Alpha(
			resolve.enabled( R.styleable.Widget_Validation_alpha ),
			resolve.message( R.styleable.Widget_Validation_alphaMessage, R.string.validation_error_alpha )
		)

		val email = new Email(
			resolve.enabled( R.styleable.Widget_Validation_email ),
			resolve.message( R.styleable.Widget_Validation_emailMessage, R.string.validation_error_email )
		)

		val matches = new Match(
			resolve.message( R.styleable.Widget_Validation_matchesMessage, R.string.validation_error_matches ),
			array.getResourceId( R.styleable.Widget_Validation_matches, -1 )
		) {
			override def find = getRootView.findViewById( target ).asInstanceOf[TextView].getText
		}


		val max = new Max(
			resolve.enabled( R.styleable.Widget_Validation_max ),
			resolve.message( R.styleable.Widget_Validation_maxMessage, R.string.validation_error_max_length ),
			array.getInt( R.styleable.Widget_Validation_max, Int.MaxValue )
		)

		val min = new Min(
			resolve.enabled( R.styleable.Widget_Validation_min ),
			resolve.message( R.styleable.Widget_Validation_minMessage, R.string.validation_error_min_length ),
			array.getInt( R.styleable.Widget_Validation_min, Int.MinValue )
		)

		val required = new Required(
			resolve.enabled( R.styleable.Widget_Validation_required ),
			resolve.message( R.styleable.Widget_Validation_requiredMessage, R.string.validation_error_required )
		)

		val all = Seq( alpha, email, matches, max, min, required )

		array.recycle()
	}

	setOnFocusChangeListener( ( _: View, focus: Boolean ) => if( !focus ) validate(): Unit )

	override def isValid = validation.all.forall( _.validate( getText.toString ) )

	override def validate() = validation.all.find( !_.validate( getText.toString ) ) match
	{
		case Some( field ) =>
		{
			validation.icon match
			{
				case Some( icon ) => setError( field.message, icon )
				case None => setError( field.message )
			}

			false
		}
		case None =>
		{
			setError( null, null )
			true
		}
	}
}