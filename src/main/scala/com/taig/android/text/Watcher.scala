package com.taig.android.text

import android.text.{Editable, TextWatcher}

abstract class Watcher extends TextWatcher
{
	override def beforeTextChanged( s: CharSequence, start: Int, count: Int, after: Int ) = {}

	override def onTextChanged( s: CharSequence, start: Int, before: Int, count: Int ) = {}

	override def afterTextChanged( s: Editable ) = {}
}