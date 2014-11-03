package com.taig.android.preference

import android.content.Context
import android.os.Build
import android.preference.Preference.OnPreferenceChangeListener
import android.preference.{ListPreference, Preference}
import android.util.AttributeSet
import com.taig.android.conversion._

class	SummarizedList( attributes: AttributeSet = null, style: Int = android.R.attr.dialogPreferenceStyle, theme: Int = 0 )( implicit context: Context )
extends	ListPreference( context, attributes, style, theme )
{
	def this( context: Context, attributes: AttributeSet, style: Int, theme: Int ) = this( attributes, style, theme )( context )

	def this( context: Context, attributes: AttributeSet, style: Int ) = this( attributes, style )( context )

	def this( context: Context, attributes: AttributeSet ) = this( attributes )( context )

	def this( context: Context ) = this()( context )

	private var listener: Option[OnPreferenceChangeListener] = None

	super.setOnPreferenceChangeListener( ( preference: Preference, newValue: Any ) =>
	{
		val result = listener.map( _.onPreferenceChange( preference, newValue ) ).getOrElse( true )
		preference.setSummary( getEntry )
		result
	} )

	override def setValue( value: String )
	{
		if( Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT )
		{
			super.setValue( value )
		}
		else
		{
			// Manually notifyChanged older versions of Android
			val old = getValue
			super.setValue( value )

			if( value != old )
			{
				notifyChanged()
			}
		}
	}

	override def getSummary = getEntry

	override def getOnPreferenceChangeListener = listener.orNull

	override def setOnPreferenceChangeListener( listener: OnPreferenceChangeListener ) = this.listener = Option( listener )
}