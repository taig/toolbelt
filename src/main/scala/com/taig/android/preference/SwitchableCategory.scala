package com.taig.android.preference

import android.content.Context
import android.content.res.TypedArray
import android.preference.PreferenceCategory
import android.util.AttributeSet
import android.view.View
import android.widget.CompoundButton
import com.taig.android.R
import com.taig.android.conversion._

class	SwitchableCategory( attributes: AttributeSet = null, style: Int = android.R.attr.preferenceCategoryStyle, theme: Int = 0 )( implicit context: Context )
extends	PreferenceCategory( context, attributes, style, theme )
{
	def this( context: Context, attributes: AttributeSet, styles: Int, theme: Int ) = this( attributes, styles, theme )( context )

	def this( context: Context, attributes: AttributeSet, styles: Int ) = this( attributes, styles )( context )

	def this( context: Context, attributes: AttributeSet ) = this( attributes )( context )

	def this( context: Context ) = this()( context )

	var checked: Boolean = false

	setLayoutResource( R.layout.switchable_category_preference )

	override def onBindView( view: View )
	{
		super.onBindView( view )

		val switch = view
			.findViewById( R.id.switchable_category_preference_widget )
			.asInstanceOf[CompoundButton]

		switch.setChecked( checked )
		switch.setOnCheckedChangeListener( ( checked: Boolean ) =>
		{
			if( !callChangeListener( checked ) ) switch.setChecked( !checked ) else setChecked( checked )
		} )

		view.setOnClickListener( ( _: View ) => switch.toggle() )
	}

	override def onGetDefaultValue( a: TypedArray, index: Int ) = java.lang.Boolean.valueOf( a.getBoolean( index, checked ) )

	override def onSetInitialValue( restore: Boolean, default: Any )
	{
		checked = if( restore ) getPersistedBoolean( checked ) else default.asInstanceOf[Boolean]
	}

	override def isEnabled = true

	override def shouldDisableDependents() = !checked

	private def setChecked( checked: Boolean )
	{
		this.checked = checked
		persistBoolean( checked )
		notifyDependencyChange( shouldDisableDependents() )
		notifyChanged()
	}
}