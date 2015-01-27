package com.taig.android.widget.recycle

import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.{Adapter, AdapterDataObserver, ViewHolder}
import android.view.View
import android.view.View.{GONE, VISIBLE}

trait	Emptyable
extends	RecyclerView
{
	protected var empty: Option[View] = None

	def setEmptyView( view: View ) = empty = Option( view )

	override def setAdapter( adapter: Adapter[_ <: ViewHolder] ): Unit =
	{
		// Unregister observer from previous adapter
		Option( getAdapter ).foreach( _.unregisterAdapterDataObserver( Observer ) )

		super.setAdapter( adapter )

		// Register observer to current adapter
		Option( adapter ).foreach( _.registerAdapterDataObserver( Observer ) )

		check()
	}

	private def check() = ( empty, Option( getAdapter() ) ) match
	{
		case ( Some( empty ), Some( adapter ) ) =>
		{
			val visible = adapter.getItemCount == 0
			empty.setVisibility( if( visible ) VISIBLE else GONE )
			setVisibility( if( visible ) GONE else VISIBLE )
		}
		case _ => // Nothing to do
	}

	private object	Observer
	extends			AdapterDataObserver
	{
		override def onItemRangeChanged( start: Int, count: Int ) = check()

		override def onItemRangeInserted( start: Int, count: Int ) = check()

		override def onItemRangeRemoved( start: Int, count: Int ) = check()
	}
}