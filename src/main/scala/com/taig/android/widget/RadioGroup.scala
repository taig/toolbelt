package com.taig.android.widget

import android.content.Context
import android.util.{TypedValue, AttributeSet}
import com.taig.android.R
import com.taig.android.widget.validation.Type.Required
import com.taig.android.widget.validation.Validatable

class	RadioGroup( val attributes: AttributeSet = null )( implicit context: Context )
extends	android.widget.RadioGroup( context, attributes )
with	Widget.Styleable
with	Validatable
{
	def this( context: Context, attributes: AttributeSet ) = this( attributes )( context )

	def this( context: Context ) = this()( context )

	override val style = android.R.attr.radioButtonStyle

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

		val required = new Required(
			resolve.enabled( R.styleable.Widget_Validation_required ),
			resolve.message( R.styleable.Widget_Validation_requiredMessage, R.string.validation_error_required_selection )
		)

		array.recycle()
	}

	override def isValid = !validation.required.enabled || validation.required.enabled && getCheckedRadioButtonId != -1

	override def validate() = isValid match
	{
		case false =>
		{
			Toast( validation.required.message ).show()
			false
		}
		case _ => true
	}
}
