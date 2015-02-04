package com.taig.android.content.fragment

import com.taig.android._
import com.taig.android.content.{Fragment, Property}

trait	Form[M]
extends	Fragment
with	Layout
{
	override val widget = new Property( this ) with Form.Widget

	def validate() = widget.form.validate()

	/**
	 * Create model M from the current form state
	 * 
	 * @return Constructed model object, ignoring validation constraints
	 */
	def load(): M

	/**
	 * Create model M from the current form state, if all fields are valid
	 * 
	 * @return Constructed model object, or <code>None</code> if validation fails
	 */
	def resolve(): Option[M] = if( validate() ) Some( load() ) else None
}

object Form
{
	trait	Widget
	extends	Property[Form[_]]
	with	Layout.Widget
	{
		lazy val form = content.find[widget.Form]( R.id.form )
	}
}