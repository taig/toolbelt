package com.taig.android.widget

object Text
{
	object Listener
	{
		trait OnEditorDone
		{
			def onEditorDone( view: android.widget.EditText ): Boolean
		}
	}
}