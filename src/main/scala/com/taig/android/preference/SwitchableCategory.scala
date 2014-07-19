package com.taig.android.preference

import android.content.Context
import android.content.res.TypedArray
import android.preference.PreferenceCategory
import android.util.AttributeSet
import android.view.View
import android.widget.CompoundButton
import com.taig.android._

class	SwitchableCategory( context: Context, attributes: AttributeSet, style: Int )
extends	PreferenceCategory( context, attributes, style )
{
	var checked: Boolean = false

	def this( context: Context ) = this( context, null, android.R.attr.preferenceCategoryStyle )

	def this( context: Context, attributes: AttributeSet ) = this( context, attributes, android.R.attr.preferenceCategoryStyle )

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