package com.taig.android.widget

import android.content.Context
import android.util.AttributeSet
import android.view.{View, ViewGroup}
import android.widget.LinearLayout
import com.taig.android.R
import com.taig.android.widget.validation.Validatable

class	Form( val attributes: AttributeSet = null, val style: Int = 0, val theme: Int = 0 )( implicit context: Context )
extends	LinearLayout( context, attributes, style, theme )
with	Widget.Styleable
with	Validatable
{
	def this( context: Context, attributes: AttributeSet, style: Int, theme: Int ) = this( attributes, style, theme )( context )

	def this( context: Context, attributes: AttributeSet, style: Int ) = this( attributes, style )( context )

	def this( context: Context, attributes: AttributeSet ) = this( attributes )( context )

	def this( context: Context ) = this()( context )

	private lazy val inputs = findChildren( this )

	if( getId == View.NO_ID )
	{
		setId( R.id.form )
	}

	/**
	 * Recursively find all validatable children of the given view
	 * 
	 * @param view View to browse
	 * @return Collection of [[Validatable]] objects
	 */
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