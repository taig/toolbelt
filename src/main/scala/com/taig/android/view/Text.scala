package com.taig.android.view

import android.widget.EditText

object Text
{
	object Listener
	{
		trait OnEditorDone
		{
			def onEditorDone( view: EditText ): Boolean
		}
	}
}