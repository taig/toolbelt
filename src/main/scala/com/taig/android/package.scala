package com.taig

import _root_.android.app.ActionBar.Tab
import _root_.android.app.{ActionBar, FragmentTransaction, SearchManager}
import _root_.android.content.{DialogInterface, SharedPreferences}
import _root_.android.os.AsyncTask
import _root_.android.preference.Preference
import _root_.android.support.v4.view.ViewPager
import _root_.android.view.View
import _root_.android.widget.CompoundButton

import scala.concurrent.ExecutionContext

package object android
{
	implicit lazy val executionContext = ExecutionContext.fromExecutor( AsyncTask.THREAD_POOL_EXECUTOR )

	implicit def `function1 -> Unit -> ActionBar.TabListener#onTabSelected`( f: ( Tab ) => Unit ) = new ActionBar.TabListener
	{
		override def onTabSelected( tab: Tab, transaction: FragmentTransaction ) = f( tab )

		override def onTabUnselected( tab: Tab, transaction: FragmentTransaction ) {}

		override def onTabReselected( tab: Tab, transaction: FragmentTransaction ) {}
	}

	implicit def `function2 -> Unit -> ActionBar.TabListener#onTabSelected`( f: ( Tab, FragmentTransaction ) => Unit ) = new ActionBar.TabListener
	{
		override def onTabSelected( tab: Tab, transaction: FragmentTransaction ) = f( tab, transaction )

		override def onTabUnselected( tab: Tab, transaction: FragmentTransaction ) {}

		override def onTabReselected( tab: Tab, transaction: FragmentTransaction ) {}
	}

	implicit def `function1 -> Unit -> CompoundButton.OnCheckedChangeListener`( f: ( Boolean ) => Unit ) = new CompoundButton.OnCheckedChangeListener
	{
		override def onCheckedChanged( widget: CompoundButton, checked: Boolean ) = f( checked )
	}

	implicit def `function2 -> Unit -> CompoundButton.OnCheckedChangeListener`( f: ( CompoundButton, Boolean ) => Unit ) = new CompoundButton.OnCheckedChangeListener
	{
		override def onCheckedChanged( widget: CompoundButton, checked: Boolean ) = f( widget, checked )
	}

	implicit def `function1 -> Unit -> DialogInterface.OnClickListener`( f: ( DialogInterface ) => Unit ) = new DialogInterface.OnClickListener
	{
		override def onClick( dialog: DialogInterface, which: Int ): Unit = f( dialog )
	}

	implicit def `function2 -> Unit -> DialogInterface.OnClickListener`( f: ( DialogInterface, Int ) => Unit ) = new DialogInterface.OnClickListener
	{
		override def onClick( dialog: DialogInterface, which: Int ): Unit = f( dialog, which )
	}

	implicit def `function1 -> Unit -> DialogInterface.OnDismissListener`( f: ( DialogInterface ) => Unit ) = new DialogInterface.OnDismissListener
	{
		override def onDismiss( dialog: DialogInterface ): Unit = f( dialog )
	}

	implicit def `function1 -> Boolean -> Preference.OnPreferenceChangeListener`( f: ( Any ) => Boolean ) = new Preference.OnPreferenceChangeListener
	{
		override def onPreferenceChange( preference: Preference, newValue: Any ) = f( newValue )
	}

	implicit def `function2 -> Boolean -> Preference.OnPreferenceChangeListener`( f: ( Preference, Any ) => Boolean ) = new Preference.OnPreferenceChangeListener
	{
		override def onPreferenceChange( preference: Preference, newValue: Any ) = f( preference, newValue )
	}

	implicit def `function0 -> Unit -> Preference.OnPreferenceClickListener`( f: () => Unit ) = new Preference.OnPreferenceClickListener
	{
		override def onPreferenceClick( preference: Preference ) = { f(); true }
	}

	implicit def `function0 -> Boolean -> Preference.OnPreferenceClickListener`( f: () => Boolean ) = new Preference.OnPreferenceClickListener
	{
		override def onPreferenceClick( preference: Preference ) = f()
	}

	implicit def `function1 -> Boolean -> Preference.OnPreferenceClickListener`( f: ( Preference ) => Boolean ) = new Preference.OnPreferenceClickListener
	{
		override def onPreferenceClick( preference: Preference ) = f( preference )
	}

	implicit def `function1 -> Unit -> Preference.OnPreferenceClickListener`( f: ( Preference ) => Unit ) = new Preference.OnPreferenceClickListener
	{
		override def onPreferenceClick( preference: Preference ) = { f( preference ); true }
	}

	implicit def `function0 -> Unit -> Runnable`( f: => Unit ) = new Runnable
	{
		override def run() = f
	}

	implicit def `function0 -> Unit -> SearchManager.OnCancelListener`( f: () => Unit ) = new SearchManager.OnCancelListener
	{
		override def onCancel() = f()
	}

	implicit def `function0 -> Unit -> SearchManager.OnDismissListener`( f: () => Unit ) = new SearchManager.OnDismissListener
	{
		override def onDismiss() = f()
	}

	implicit def `function2 -> Unit -> SharedPreferences.OnSharedPreferenceChangeListener`( f: ( SharedPreferences, String ) => Unit ) = new SharedPreferences.OnSharedPreferenceChangeListener
	{
		override def onSharedPreferenceChanged( preferences: SharedPreferences, key: String ) = f( preferences, key )
	}

	implicit def `function1 -> Unit -> View.OnClickListener`( f: ( View ) => Unit ) = new View.OnClickListener
	{
		override def onClick( view: View ) = f( view )
	}

	implicit def `function1 -> Boolean -> View.OnLongClickListener`( f: ( View ) => Boolean ) = new View.OnLongClickListener
	{
		override def onLongClick( view: View ) = f( view )
	}

	implicit def `function1 -> Unit -> ViewPager.OnPageChangeListener#onPageSelected`( f: ( Int ) => Unit ) = new ViewPager.OnPageChangeListener
	{
		override def onPageScrolled( position: Int, positionOffset: Float, positionOffsetPixels: Int ) {}

		override def onPageScrollStateChanged( state: Int ) {}

		override def onPageSelected( position: Int ) = f( position )
	}
}