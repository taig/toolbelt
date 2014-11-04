package com.taig.android.widget

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.{AttributeSet, TypedValue}
import android.view.View
import android.widget.TextView
import com.taig.android._
import com.taig.android.conversion._
import com.taig.android.widget.validation.Type._
import com.taig.android.widget.validation.{Validatable, Validator}

/**
 * An EditText implementation that allows form validation
 */
class	EditText( val attributes: AttributeSet = null, val style: Int = android.R.attr.editTextStyle )( implicit context: Context )
extends	android.widget.EditText( context, attributes, style )
with	Widget.Styleable
with	Validatable
{
	def this( context: Context, attributes: AttributeSet, style: Int ) = this( attributes, style )( context )

	def this( context: Context, attributes: AttributeSet ) = this( attributes )( context )

	def this( context: Context ) = this()( context )

	val validation = new
	{
		private val array = getStyledAttributes( R.styleable.Widget_Validation )

		private val resolve = new
		{
			private val value = new TypedValue

			def enabled( index: Int ) = if( array.getValue( index, value ) )
			{
				value.`type` match
				{
					case TypedValue.TYPE_INT_BOOLEAN => array.getBoolean( index, false )
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

		var icon = Option( array.getDrawable( R.styleable.Widget_Validation_icon ) )

		val alpha = new Alpha(
			resolve.enabled( R.styleable.Widget_Validation_alpha ),
			resolve.message( R.styleable.Widget_Validation_alphaMessage, R.string.validation_error_alpha )
		)

		val alphaDash = new AlphaDash(
			resolve.enabled( R.styleable.Widget_Validation_alphaDash ),
			resolve.message( R.styleable.Widget_Validation_alphaDashMessage, R.string.validation_error_alpha_dash )
		)

		val alphaNumeric = new AlphaNumeric(
			resolve.enabled( R.styleable.Widget_Validation_alphaNumeric ),
			resolve.message( R.styleable.Widget_Validation_alphaNumericMessage, R.string.validation_error_alpha_numeric )
		)

		val email = new Email(
			resolve.enabled( R.styleable.Widget_Validation_email ),
			resolve.message( R.styleable.Widget_Validation_emailMessage, R.string.validation_error_email )
		)

		val integer = new Integer(
			resolve.enabled( R.styleable.Widget_Validation_integer ),
			resolve.message( R.styleable.Widget_Validation_integerMessage, R.string.validation_error_integer )
		)

		val length = new Length(
			resolve.message( R.styleable.Widget_Validation_lengthMessage, R.string.validation_error_length ),
			array.getInt( R.styleable.Widget_Validation_length, -1 )
		)

		val matches = new Matches(
			resolve.message( R.styleable.Widget_Validation_matchesMessage, R.string.validation_error_matches ),
			array.getResourceId( R.styleable.Widget_Validation_matches, -1 )
		) {
			override def find = getRootView.findViewById( target ).asInstanceOf[TextView].getText
		}

		val max = new Max(
			Option( array.getString( R.styleable.Widget_Validation_maxMessage ) )
				.getOrElse( context.getString( R.string.validation_error_max_length ) ),
			array.getInt( R.styleable.Widget_Validation_max, Int.MaxValue )
		)

		val min = new Min(
			Option( array.getString( R.styleable.Widget_Validation_minMessage ) )
				.getOrElse( context.getString( R.string.validation_error_min_length ) ),
			array.getInt( R.styleable.Widget_Validation_min, Int.MinValue )
		)

		val numeric = new Numeric(
			resolve.enabled( R.styleable.Widget_Validation_numeric ),
			resolve.message( R.styleable.Widget_Validation_numericMessage, R.string.validation_error_numeric )
		)

		val phone = new Phone(
			resolve.enabled( R.styleable.Widget_Validation_phone ),
			resolve.message( R.styleable.Widget_Validation_phoneMessage, R.string.validation_error_phone )
		)

		val regex = new Validator.Regex(
			resolve.enabled( R.styleable.Widget_Validation_regex ) || array.getString( R.styleable.Widget_Validation_pattern ) != null,
			resolve.message( R.styleable.Widget_Validation_regexMessage, R.string.validation_error_regex ),
			array.getString( R.styleable.Widget_Validation_pattern )
		)

		val required = new Required(
			resolve.enabled( R.styleable.Widget_Validation_required ),
			resolve.message( R.styleable.Widget_Validation_requiredMessage, R.string.validation_error_required )
		)

		val all = Seq( alpha, alphaDash, alphaNumeric, email, integer, length, matches, max, min, numeric, phone, regex, required )

		array.recycle()
	}

	setOnFocusChangeListener( ( _: View, focus: Boolean ) => if( !focus ) validate(): Unit )

	override def setError( error: CharSequence ) = validation.icon match
	{
		case Some( icon ) => setError( error, icon )
		case None => super.setError( error )
	}

	override def setError( error: CharSequence, icon: Drawable )
	{
		Option( icon ).foreach( iocn => icon.setBounds( 0, 0, icon.getIntrinsicWidth, icon.getIntrinsicHeight ) )
		super.setError( error, icon )
	}

	override def isValid = validation.all.forall( _.validate( getText.toString ) )

	override def validate() = validation.all.find( !_.validate( getText.toString ) ) match
	{
		case Some( field ) => setError( field.message ); false
		case None => setError( null, null ); true
	}
}