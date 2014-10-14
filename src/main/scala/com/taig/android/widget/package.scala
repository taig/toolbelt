package com.taig.android

import android.view.inputmethod.EditorInfo
import android.widget.{EditText, TextView}
import com.taig.android.conversion._

package object widget
{
	implicit class RichTextView( view: TextView )
	{
		def setOnEditorDoneListener( listener: Text.Listener.OnEditorDone ) =
		{
			view.setOnEditorActionListener( ( view: EditText, action: Int ) => action & EditorInfo.IME_MASK_ACTION match
			{
				case EditorInfo.IME_ACTION_DONE => listener.onEditorDone( view )
				case _ => false
			} )
		}
	}
}