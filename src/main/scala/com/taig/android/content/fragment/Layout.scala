package com.taig.android.content.fragment

import android.os.Bundle
import android.view.{LayoutInflater, View, ViewGroup}
import com.taig.android.content
import com.taig.android.content.{Property, Fragment}

trait	Layout
extends	Fragment
{
	protected implicit def `Int -> Layout.Property`( id: Int ) = new Property( this ) with Layout.Property
	{
		override def reference = Left( id )
	}

	protected implicit def `View -> Layout.Property`( view: View ) = new Property( this ) with Layout.Property
	{
		override def reference = Right( view )
	}

	def layout: Layout.Property

	def widget: Layout.Widget

	override def onCreateView( inflater: LayoutInflater, container: ViewGroup, state: Bundle ) = layout.reference match
	{
		case Left( id ) => inflater.inflate( id, container, false )
		case Right( view ) => view
	}
}

object Layout
{
	trait	Property
	extends	content.Property[Layout]
	{
		implicit protected def `Int -> Either`( id: Int ): Either[Int, View] = Left( id )

		implicit protected def `View -> Either`( view: View ): Either[Int, View] = Right( view )

		/**
		 * Either a layout id (Int), or an actual view instance (android.view.View)
		 */
		def reference: Either[Int, View]
	}

	trait	Widget
	extends	content.Property[Layout]
}