package com.taig.android

import android.view.inputmethod.EditorInfo
import android.widget.{EditText, TextView, ViewSwitcher}
import com.taig.android.conversion._

package object widget
{
	implicit class RichTextView( view: TextView )
	{
		/**
		 * A callback to be triggered when the software keyboard 'DONE' button is clicked while this TextView has
		 * focus
		 * 
		 * @param listener The listener to trigger
		 * @see [[android.widget.TextView.OnEditorActionListener]]
		 */
		def setOnEditorDoneListener( listener: Text.Listener.OnEditorDone ) =
		{
			view.setOnEditorActionListener( ( view: EditText, action: Int ) => action & EditorInfo.IME_MASK_ACTION match
			{
				case EditorInfo.IME_ACTION_DONE => listener.onEditorDone( view )
				case _ => false
			} )
		}
	}

	implicit class RichViewSwitcher( view: ViewSwitcher )
	{
		def toggle() = view.setDisplayedChild( ( view.getDisplayedChild + 1 ) % 2 )
	}
}