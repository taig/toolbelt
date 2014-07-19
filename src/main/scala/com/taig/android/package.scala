package com.taig

import _root_.android.app.SearchManager
import _root_.android.content.{SharedPreferences, DialogInterface}
import _root_.android.preference.Preference
import _root_.android.view.View
import _root_.android.widget.CompoundButton

package object android
{
	implicit def `function1 -> CompoundButton.OnCheckedChangeListener`( f: ( Boolean ) => Unit ) = new CompoundButton.OnCheckedChangeListener
	{
		override def onCheckedChanged( widget: CompoundButton, checked: Boolean ) = f( checked )
	}

	implicit def `function2 -> CompoundButton.OnCheckedChangeListener`( f: ( CompoundButton, Boolean ) => Unit ) = new CompoundButton.OnCheckedChangeListener
	{
		override def onCheckedChanged( widget: CompoundButton, checked: Boolean ) = f( widget, checked )
	}

	implicit def `function1 -> DialogInterface.OnClickListener`( f: ( DialogInterface ) => Unit ) = new DialogInterface.OnClickListener
	{
		override def onClick( dialog: DialogInterface, which: Int ): Unit = f( dialog )
	}

	implicit def `function2 -> DialogInterface.OnClickListener`( f: ( DialogInterface, Int ) => Unit ) = new DialogInterface.OnClickListener
	{
		override def onClick( dialog: DialogInterface, which: Int ): Unit = f( dialog, which )
	}

	implicit def `function1 -> Preference.OnPreferenceChangeListener`( f: ( Any ) => Boolean ) = new Preference.OnPreferenceChangeListener
	{
		override def onPreferenceChange( preference: Preference, newValue: Any ) = f( newValue )
	}

	implicit def `function2 -> Preference.OnPreferenceChangeListener`( f: ( Preference, Any ) => Boolean ) = new Preference.OnPreferenceChangeListener
	{
		override def onPreferenceChange( preference: Preference, newValue: Any ) = f( preference, newValue )
	}

	implicit def `function0 -> Runnable`( f: () => Unit ) = new Runnable
	{
		override def run() = f()
	}

	implicit def `function0 -> SearchManager.OnCancelListener`( f: () => Unit ) = new SearchManager.OnCancelListener
	{
		override def onCancel() = f()
	}

	implicit def `function0 -> SearchManager.OnDismissListener`( f: () => Unit ) = new SearchManager.OnDismissListener
	{
		override def onDismiss() = f()
	}

	implicit def `function0 -> SharedPreferences.OnSharedPreferenceChangeListener`( f: ( SharedPreferences, String ) => Unit ) = new SharedPreferences.OnSharedPreferenceChangeListener
	{
		override def onSharedPreferenceChanged( preferences: SharedPreferences, key: String ) = f( preferences, key )
	}

	implicit def `function1 -> View.OnClickListener`( f: ( View ) => Unit ) = new View.OnClickListener
	{
		override def onClick( view: View ) = f( view )
	}

	implicit def `function1 -> View.OnLongClickListener`( f: ( View ) => Boolean ) = new View.OnLongClickListener
	{
		override def onLongClick( view: View ) = f( view )
	}
}