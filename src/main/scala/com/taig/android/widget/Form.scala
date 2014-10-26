package com.taig.android.widget

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import android.widget.LinearLayout
import com.taig.android.R
import com.taig.android.widget.validation.Validatable

class	Form( val context: Context, attributes: AttributeSet, style: Int )
extends	LinearLayout( context, attributes, style )
with	Widget
with	Validatable
{
	def this( context: Context, attributes: AttributeSet ) = this( context, attributes, 0 )

	def this( context: Context ) = this( context, null )

	setId( R.id.form )

	lazy val inputs = findChildren( this )

	private def findChildren( view: ViewGroup ): Seq[Validatable] = ( 0 to view.getChildCount - 1 )
		.map( view.getChildAt )
		.collect
		{
			case validatable: Validatable => Seq( validatable )
			case group: ViewGroup => findChildren( group )
		}
		.flatten

	override def isValid = inputs.forall( _.isValid )

	override def validate() = inputs.foldLeft( true ){ case ( status, field ) => field.validate() && status }
}