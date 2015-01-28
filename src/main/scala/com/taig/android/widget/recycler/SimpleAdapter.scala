package com.taig.android.widget.recycler

import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.ViewHolder
import android.view.{View, ViewGroup}
import com.taig.android.widget.Settable
import com.taig.android.widget.recycler.SimpleAdapter.Holder

trait	SimpleAdapter[M]
extends	RecyclerView.Adapter[SimpleAdapter.Holder[M]]
{
	def getItem( position: Int ): M

	def onCreateView( view: ViewGroup, `type`: Int ): View with Settable[M]

	override def onCreateViewHolder( view: ViewGroup, `type`: Int ) = new Holder[M]( onCreateView( view, `type` ) )

	override final def onBindViewHolder( holder: Holder[M], position: Int ) =
	{
		onBindViewHolder( holder.view, getItem( position ) )
	}

	def onBindViewHolder( view: View with Settable[M], model: M ): Unit =
	{
		view.set( model )
	}
}

object SimpleAdapter
{
	class	Holder[M]( val view: View with Settable[M] )
	extends	ViewHolder( view )
}