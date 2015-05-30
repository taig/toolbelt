package com.taig.android

import android.app.{TimePickerDialog, SearchManager}
import android.content.{DialogInterface, SharedPreferences}
import android.preference.Preference
import android.view.{KeyEvent, MenuItem, View, ViewTreeObserver}
import android.widget.{TimePicker, AdapterView, CompoundButton, TextView}

import scala.language.implicitConversions

package object conversion
{
	implicit def `Function0 -> Unit -> AdapterView.OnItemClickListener`( f: => Unit ) = new AdapterView.OnItemClickListener
	{
		override def onItemClick( parent: AdapterView[_], view: View, position: Int, id: Long ) = f
	}

	implicit def `Function4 -> Unit -> AdapterView.OnItemClickListener`( f: ( AdapterView[_], View, Int, Long ) => Unit ) = new AdapterView.OnItemClickListener
	{
		override def onItemClick( parent: AdapterView[_], view: View, position: Int, id: Long ) = f( parent, view, position, id )
	}

	implicit def `Function0 -> Boolean -> AdapterView.OnItemLongClickListener`( f: => Boolean ) = new AdapterView.OnItemLongClickListener
	{
		override def onItemLongClick( parent: AdapterView[_], view: View, position: Int, id: Long ) = f
	}

	implicit def `Function4 -> Boolean -> AdapterView.OnItemLongClickListener`( f: ( AdapterView[_], View, Int, Long ) => Boolean ) = new AdapterView.OnItemLongClickListener
	{
		override def onItemLongClick( parent: AdapterView[_], view: View, position: Int, id: Long ) = f( parent, view, position, id )
	}

	implicit def `Function1 -> Unit -> CompoundButton.OnCheckedChangeListener`( f: Boolean => Unit ) = new CompoundButton.OnCheckedChangeListener
	{
		override def onCheckedChanged( widget: CompoundButton, checked: Boolean ) = f( checked )
	}

	implicit def `Function2 -> Unit -> CompoundButton.OnCheckedChangeListener`( f: ( CompoundButton, Boolean ) => Unit ) = new CompoundButton.OnCheckedChangeListener
	{
		override def onCheckedChanged( widget: CompoundButton, checked: Boolean ) = f( widget, checked )
	}

	implicit def `Function0 -> Unit -> DialogInterface.OnCancelListener`( f: => Unit ) = new DialogInterface.OnCancelListener
	{
		override def onCancel( dialog: DialogInterface ) = f
	}

	implicit def `Function1 -> Unit -> DialogInterface.OnCancelListener`( f: DialogInterface => Unit ) = new DialogInterface.OnCancelListener
	{
		override def onCancel( dialog: DialogInterface ) = f( dialog )
	}

	implicit def `Function0 -> Unit -> DialogInterface.OnClickListener`( f: => Unit ) = new DialogInterface.OnClickListener
	{
		override def onClick( dialog: DialogInterface, which: Int ) = f
	}

	implicit def `Function1 -> Unit -> DialogInterface.OnClickListener`( f: DialogInterface => Unit ) = new DialogInterface.OnClickListener
	{
		override def onClick( dialog: DialogInterface, which: Int ) = f( dialog )
	}

	implicit def `Function2 -> Unit -> DialogInterface.OnClickListener`( f: ( DialogInterface, Int ) => Unit ) = new DialogInterface.OnClickListener
	{
		override def onClick( dialog: DialogInterface, which: Int ) = f( dialog, which )
	}

	implicit def `Function0 -> Unit -> DialogInterface.OnDismissListener`( f: => Unit ) = new DialogInterface.OnDismissListener
	{
		override def onDismiss( dialog: DialogInterface ) = f
	}

	implicit def `Function1 -> Unit -> DialogInterface.OnDismissListener`( f: DialogInterface => Unit ) = new DialogInterface.OnDismissListener
	{
		override def onDismiss( dialog: DialogInterface ) = f( dialog )
	}

	implicit def `Function0 -> Boolean -> MenuItem.OnMenuItemClickListener`( f: => Boolean ) = new MenuItem.OnMenuItemClickListener
	{
		override def onMenuItemClick( item: MenuItem ) = f
	}

	implicit def `Function0 -> Unit -> MenuItem.OnMenuItemClickListener`( f: => Unit ) = new MenuItem.OnMenuItemClickListener
	{
		override def onMenuItemClick( item: MenuItem ) = { f; false }
	}

	implicit def `Function1 -> Boolean -> MenuItem.OnMenuItemClickListener`( f: ( MenuItem ) => Boolean ) = new MenuItem.OnMenuItemClickListener
	{
		override def onMenuItemClick( item: MenuItem ) = f( item )
	}

	implicit def `Function1 -> Unit -> MenuItem.OnMenuItemClickListener`( f: ( MenuItem ) => Unit ) = new MenuItem.OnMenuItemClickListener
	{
		override def onMenuItemClick( item: MenuItem ) = { f( item ); false }
	}

	implicit def `Function1 -> Boolean -> Preference.OnPreferenceChangeListener`( f: Any => Boolean ) = new Preference.OnPreferenceChangeListener
	{
		override def onPreferenceChange( preference: Preference, newValue: Any ) = f( newValue )
	}

	implicit def `Function2 -> Boolean -> Preference.OnPreferenceChangeListener`( f: ( Preference, Any ) => Boolean ) = new Preference.OnPreferenceChangeListener
	{
		override def onPreferenceChange( preference: Preference, newValue: Any ) = f( preference, newValue )
	}

	implicit def `Function0 -> Boolean -> Preference.OnPreferenceClickListener`( f: => Boolean ) = new Preference.OnPreferenceClickListener
	{
		override def onPreferenceClick( preference: Preference ) = f
	}

	implicit def `Function1 -> Boolean -> Preference.OnPreferenceClickListener`( f: Preference => Boolean ) = new Preference.OnPreferenceClickListener
	{
		override def onPreferenceClick( preference: Preference ) = f( preference )
	}

	implicit def `Function0 -> Unit -> Runnable`( f: => Unit ) = new Runnable
	{
		override def run() = f
	}

	implicit def `Function0 -> Unit -> SearchManager.OnCancelListener`( f: => Unit ) = new SearchManager.OnCancelListener
	{
		override def onCancel() = f
	}

	implicit def `Function0 -> Unit -> SearchManager.OnDismissListener`( f: => Unit ) = new SearchManager.OnDismissListener
	{
		override def onDismiss() = f
	}

	implicit def `Function2 -> Unit -> SharedPreferences.OnSharedPreferenceChangeListener`( f: ( SharedPreferences, String ) => Unit ) = new SharedPreferences.OnSharedPreferenceChangeListener
	{
		override def onSharedPreferenceChanged( preferences: SharedPreferences, key: String ) = f( preferences, key )
	}

	implicit def `Function0 -> Boolean -> TextView.OnEditorActionListener`( f: => Boolean ) = new TextView.OnEditorActionListener
	{
		override def onEditorAction( view: TextView, action: Int, event: KeyEvent ) = f
	}

	implicit def `Function1 -> Boolean -> TextView.OnEditorActionListener`[T <: TextView]( f: ( T ) => Boolean ) = new TextView.OnEditorActionListener
	{
		override def onEditorAction( view: TextView, action: Int, event: KeyEvent ) = f( view.asInstanceOf[T] )
	}

	implicit def `Function2 -> Boolean -> TextView.OnEditorActionListener`[T <: TextView]( f: ( T, Int ) => Boolean ) = new TextView.OnEditorActionListener
	{
		override def onEditorAction( view: TextView, action: Int, event: KeyEvent ) = f( view.asInstanceOf[T], action )
	}

	implicit def `Function3 -> Boolean -> TextView.OnEditorActionListener`[T <: TextView]( f: ( T, Int, KeyEvent ) => Boolean ) = new TextView.OnEditorActionListener
	{
		override def onEditorAction( view: TextView, action: Int, event: KeyEvent ) = f( view.asInstanceOf[T], action, event )
	}

	implicit def `Function2 -> Unit -> TimePickerDialog.OnTimeSetListener`( f: ( Int, Int ) => Unit ) =
	{
		new TimePickerDialog.OnTimeSetListener
		{
			override def onTimeSet( view: TimePicker, hour: Int, minute: Int ) = f( hour, minute )
		}
	}

	implicit def `Function3 -> Unit -> TimePickerDialog.OnTimeSetListener`( f: ( TimePicker, Int, Int ) => Unit ) =
	{
		new TimePickerDialog.OnTimeSetListener
		{
			override def onTimeSet( view: TimePicker, hour: Int, minute: Int ) = f( view, hour, minute )
		}
	}

	implicit def `Function0 -> Unit -> View.OnClickListener`( f: => Unit ) = new View.OnClickListener
	{
		override def onClick( view: View ) = f
	}

	implicit def `Function1 -> Unit -> View.OnClickListener`( f: View => Unit ) = new View.OnClickListener
	{
		override def onClick( view: View ) = f( view )
	}

	implicit def `Function2 -> Unit -> View.OnFocusChangeListener`( f: ( View, Boolean ) => Unit ) = new View.OnFocusChangeListener
	{
		override def onFocusChange( view: View, hasFocus: Boolean ) = f( view, hasFocus )
	}

	implicit def `Function0 -> Unit -> View.OnLayoutChangedListener`( f: => Unit ) = new View.OnLayoutChangeListener
	{
		override def onLayoutChange( view: View, left: Int, top: Int, right: Int, bottom: Int, oldLeft: Int, oldTop: Int, oldRight: Int, oldBottom: Int ) = f
	}

	implicit def `Function1 -> Unit -> View.OnLayoutChangedListener`( f: ( View ) => Unit ) = new View.OnLayoutChangeListener
	{
		override def onLayoutChange( view: View, left: Int, top: Int, right: Int, bottom: Int, oldLeft: Int, oldTop: Int, oldRight: Int, oldBottom: Int ) =
		{
			f( view )
		}
	}

	implicit def `Function9 -> Unit -> View.OnLayoutChangedListener`( f: ( View, Int, Int, Int, Int, Int, Int, Int, Int ) => Unit ) = new View.OnLayoutChangeListener
	{
		override def onLayoutChange( view: View, left: Int, top: Int, right: Int, bottom: Int, oldLeft: Int, oldTop: Int, oldRight: Int, oldBottom: Int ) =
		{
			f( view, left, top, right, bottom, oldLeft, oldTop, oldRight, oldBottom )
		}
	}

	implicit def `Function1 -> Boolean -> View.OnLongClickListener`( f: View => Boolean ) = new View.OnLongClickListener
	{
		override def onLongClick( view: View ) = f( view )
	}

	implicit def `Function0 -> Unit -> ViewTreeObserver.OnDrawListener`( f: => Unit ) = new ViewTreeObserver.OnDrawListener()
	{
		override def onDraw() = f
	}

	implicit def `Function2 -> Unit -> ViewTreeObserver.OnGlobalFocusChangeListener`( f: ( View, View ) => Unit ) = new ViewTreeObserver.OnGlobalFocusChangeListener()
	{
		override def onGlobalFocusChanged( oldFocus: View, newFocus: View ) = f( oldFocus, newFocus )
	}

	implicit def `Function0 -> Unit -> ViewTreeObserver.OnGlobalLayoutListener`( f: => Unit ) = new ViewTreeObserver.OnGlobalLayoutListener()
	{
		override def onGlobalLayout() = f
	}

	implicit def `Function0 -> Boolean -> ViewTreeObserver.OnPreDrawListener`( f: => Boolean ) = new ViewTreeObserver.OnPreDrawListener()
	{
		override def onPreDraw() = f
	}

	implicit def `Function0 -> Unit -> ViewTreeObserver.OnScrollChangedListener`( f: => Unit ) = new ViewTreeObserver.OnScrollChangedListener()
	{
		override def onScrollChanged() = f
	}

	implicit def `Function1 -> Unit -> ViewTreeObserver.OnTouchModeChangeListener`( f: Boolean => Unit ) = new ViewTreeObserver.OnTouchModeChangeListener()
	{
		override def onTouchModeChanged( isInTouchMode: Boolean ) = f( isInTouchMode )
	}

	implicit def `Function1 -> Unit -> ViewTreeObserver.OnWindowFocusChangeListener`( f: Boolean => Unit ) = new ViewTreeObserver.OnWindowFocusChangeListener()
	{
		override def onWindowFocusChanged( hasFocus: Boolean ) = f( hasFocus )
	}
}