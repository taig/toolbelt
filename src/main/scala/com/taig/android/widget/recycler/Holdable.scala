package com.taig.android.widget.recycler

import android.support.v7.widget.RecyclerView.ViewHolder
import android.view.View

trait	Holdable
extends	View
{
	object	Holder
	extends	ViewHolder( this )
}
