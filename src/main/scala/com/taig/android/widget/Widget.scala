package com.taig.android.widget

import android.os.Parcelable
import android.view.View
import com.taig.android.content.Context

trait	Widget
extends	View
with	Context
{
	override def onSaveInstanceState() = super.onSaveInstanceState()

	override def onRestoreInstanceState( state: Parcelable ) = super.onRestoreInstanceState( state )
}