package com.taig.android.content.fragment

import android.os.Bundle
import android.view.View
import android.widget.{ListView, TextView}
import com.taig.android._
import com.taig.android.content.{Fragment, Property}

trait	List
extends	com.taig.android.content.Fragment.List
with	Fragment
with	Layout
{
	protected implicit def `String -> List.Property`( string: String ): List.Property =
	{
		new Property( this ) with List.Property
		{
			override def empty = string
		}
	}

	def list: List.Property

	override val layout = new Property( this ) with List.Layout

	override val widget = new Property( this ) with List.Widget

	override def onViewCreated( view: View, state: Bundle )
	{
		super.onViewCreated( view, state )

		this.widget.empty.setText( list.empty )
	}
}

object List
{
	trait	Property
	extends	content.Property[List]
	{
		/**
		 * The message to show when the list is empty
		 */
		def empty: String
	}

	trait	Widget
	extends	content.Property[List]
	with	Layout.Widget
	{
		lazy val empty = content.findViewById( android.R.id.empty ).asInstanceOf[TextView]

		lazy val list = content.findViewById( android.R.id.list ).asInstanceOf[ListView]
	}

	trait	Layout
	extends	content.Property[List]
	with	Layout.Property
	{
		override val reference: Either[Int, View] = R.layout.list
	}
}