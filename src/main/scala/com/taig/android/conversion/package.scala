package com.taig.android

import android.app.ActionBar.Tab
import android.app.{ActionBar, FragmentTransaction, SearchManager}
import android.content.{DialogInterface, SharedPreferences}
import android.preference.Preference
import android.support.v4.view.ViewPager
import android.view.{ViewTreeObserver, MenuItem, KeyEvent, View}
import android.widget.{EditText, TextView, AdapterView, CompoundButton}
import com.taig.android.widget.Text

package object conversion
{
	implicit def `Function1 -> Unit -> ActionBar.TabListener#onTabSelected`( f: Tab => Any ) = new ActionBar.TabListener
	{
		override def onTabSelected( tab: Tab, transaction: FragmentTransaction ) = f( tab )

		override def onTabUnselected( tab: Tab, transaction: FragmentTransaction ) {}

		override def onTabReselected( tab: Tab, transaction: FragmentTransaction ) {}
	}

	implicit def `Function2 -> Unit -> ActionBar.TabListener#onTabSelected`( f: ( Tab, FragmentTransaction ) => Any ) = new ActionBar.TabListener
	{
		override def onTabSelected( tab: Tab, transaction: FragmentTransaction ) = f( tab, transaction )

		override def onTabUnselected( tab: Tab, transaction: FragmentTransaction ) {}

		override def onTabReselected( tab: Tab, transaction: FragmentTransaction ) {}
	}

	implicit def `Function0 -> Unit -> AdapterView.OnItemClickListener`( f: => Any ) = new AdapterView.OnItemClickListener
	{
		override def onItemClick( parent: AdapterView[_], view: View, position: Int, id: Long ) = f
	}

	implicit def `Function4 -> Unit -> AdapterView.OnItemClickListener`( f: ( AdapterView[_], View, Int, Long ) => Any ) = new AdapterView.OnItemClickListener
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

	implicit def `Function1 -> Unit -> CompoundButton.OnCheckedChangeListener`( f: Boolean => Any ) = new CompoundButton.OnCheckedChangeListener
	{
		override def onCheckedChanged( widget: CompoundButton, checked: Boolean ) = f( checked )
	}

	implicit def `Function2 -> Unit -> CompoundButton.OnCheckedChangeListener`( f: ( CompoundButton, Boolean ) => Any ) = new CompoundButton.OnCheckedChangeListener
	{
		override def onCheckedChanged( widget: CompoundButton, checked: Boolean ) = f( widget, checked )
	}

	implicit def `Function0 -> Unit -> DialogInterface.OnCancelListener`( f: => Any ) = new DialogInterface.OnCancelListener
	{
		override def onCancel( dialog: DialogInterface ) = f
	}

	implicit def `Function1 -> Unit -> DialogInterface.OnCancelListener`( f: DialogInterface => Any ) = new DialogInterface.OnCancelListener
	{
		override def onCancel( dialog: DialogInterface ) = f( dialog )
	}

	implicit def `Function0 -> Unit -> DialogInterface.OnClickListener`( f: => Any ) = new DialogInterface.OnClickListener
	{
		override def onClick( dialog: DialogInterface, which: Int ) = f
	}

	implicit def `Function1 -> Unit -> DialogInterface.OnClickListener`( f: DialogInterface => Any ) = new DialogInterface.OnClickListener
	{
		override def onClick( dialog: DialogInterface, which: Int ) = f( dialog )
	}

	implicit def `Function2 -> Unit -> DialogInterface.OnClickListener`( f: ( DialogInterface, Int ) => Any ) = new DialogInterface.OnClickListener
	{
		override def onClick( dialog: DialogInterface, which: Int ) = f( dialog, which )
	}

	implicit def `Function0 -> Unit -> DialogInterface.OnDismissListener`( f: => Any ) = new DialogInterface.OnDismissListener
	{
		override def onDismiss( dialog: DialogInterface ) = f
	}

	implicit def `Function1 -> Unit -> DialogInterface.OnDismissListener`( f: DialogInterface => Any ) = new DialogInterface.OnDismissListener
	{
		override def onDismiss( dialog: DialogInterface ) = f( dialog )
	}

	implicit def `Function0 -> Boolean -> MenuItem.OnMenuItemClickListener`( f: => Boolean ) = new MenuItem.OnMenuItemClickListener
	{
		override def onMenuItemClick( item: MenuItem ) = f
	}

	implicit def `Function0 -> Unit -> MenuItem.OnMenuItemClickListener`( f: => Any ) = new MenuItem.OnMenuItemClickListener
	{
		override def onMenuItemClick( item: MenuItem ) = { f; false }
	}

	implicit def `Function1 -> Boolean -> MenuItem.OnMenuItemClickListener`( f: ( MenuItem ) => Boolean ) = new MenuItem.OnMenuItemClickListener
	{
		override def onMenuItemClick( item: MenuItem ) = f( item )
	}

	implicit def `Function1 -> Unit -> MenuItem.OnMenuItemClickListener`( f: ( MenuItem ) => Any ) = new MenuItem.OnMenuItemClickListener
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

	implicit def `Function0 -> Unit -> Preference.OnPreferenceClickListener`( f: => Any ) = new Preference.OnPreferenceClickListener
	{
		override def onPreferenceClick( preference: Preference ) = { f; true }
	}

	implicit def `Function0 -> Boolean -> Preference.OnPreferenceClickListener`( f: => Boolean ) = new Preference.OnPreferenceClickListener
	{
		override def onPreferenceClick( preference: Preference ) = f
	}

	implicit def `Function1 -> Boolean -> Preference.OnPreferenceClickListener`( f: Preference => Boolean ) = new Preference.OnPreferenceClickListener
	{
		override def onPreferenceClick( preference: Preference ) = f( preference )
	}

	implicit def `Function1 -> Unit -> Preference.OnPreferenceClickListener`( f: Preference => Any ) = new Preference.OnPreferenceClickListener
	{
		override def onPreferenceClick( preference: Preference ) = { f( preference ); true }
	}

	implicit def `Function0 -> Unit -> Runnable`( f: => Any ) = new Runnable
	{
		override def run() = f
	}

	implicit def `Function0 -> Unit -> SearchManager.OnCancelListener`( f: => Any ) = new SearchManager.OnCancelListener
	{
		override def onCancel() = f
	}

	implicit def `Function0 -> Unit -> SearchManager.OnDismissListener`( f: => Any ) = new SearchManager.OnDismissListener
	{
		override def onDismiss() = f
	}

	implicit def `Function2 -> Unit -> SharedPreferences.OnSharedPreferenceChangeListener`( f: ( SharedPreferences, String ) => Any ) = new SharedPreferences.OnSharedPreferenceChangeListener
	{
		override def onSharedPreferenceChanged( preferences: SharedPreferences, key: String ) = f( preferences, key )
	}

	implicit def `Function1 -> Boolean -> Text.Listener.OnEditorDone`( f: ( EditText ) => Boolean ) = new Text.Listener.OnEditorDone()
	{
		override def onEditorDone( view: EditText ) = f( view )
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

	implicit def `Function0 -> Unit -> View.OnClickListener`( f: => Any ) = new View.OnClickListener
	{
		override def onClick( view: View ) = f
	}

	implicit def `Function1 -> Unit -> View.OnClickListener`( f: View => Any ) = new View.OnClickListener
	{
		override def onClick( view: View ) = f( view )
	}

	implicit def `Function1 -> Boolean -> View.OnLongClickListener`( f: View => Boolean ) = new View.OnLongClickListener
	{
		override def onLongClick( view: View ) = f( view )
	}

	implicit def `Function1 -> Unit -> ViewPager.OnPageChangeListener#onPageSelected`( f: Int => Any ) = new ViewPager.OnPageChangeListener
	{
		override def onPageScrolled( position: Int, positionOffset: Float, positionOffsetPixels: Int ) {}

		override def onPageScrollStateChanged( state: Int ) {}

		override def onPageSelected( position: Int ) = f( position )
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