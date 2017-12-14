package io.taig.android.util

import android.text.TextWatcher

abstract class SimpleTextWatcher extends TextWatcher {
    override def onTextChanged( s: CharSequence, start: Int, before: Int, count: Int ): Unit = {}

    override def beforeTextChanged( s: CharSequence, start: Int, count: Int, after: Int ): Unit = {}
}