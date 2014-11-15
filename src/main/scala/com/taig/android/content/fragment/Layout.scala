package com.taig.android.content.fragment

import android.os.Bundle
import android.view.{View, LayoutInflater, ViewGroup}
import com.taig.android.content
import com.taig.android.content.Fragment

trait	Layout
extends	Fragment
{
	def layout: Layout.Property[Layout]

	override def onCreateView( inflater: LayoutInflater, container: ViewGroup, state: Bundle ) = layout.reference match
	{
		case Left( id ) => inflater.inflate( id, container, false )
		case Right( view ) => view
	}
}

object Layout
{
	trait	Property[+L <: Layout]
	extends	content.Property[L]
	{
		implicit protected def `Int -> Either`( id: Int ): Either[Int, View] = Left( id )

		implicit protected def `View -> Either`( view: View ): Either[Int, View] = Right( view )

		/**
		 * Either a layout id (Int), or an actual view instance (android.view.View)
		 */
		def reference: Either[Int, View]
	}
}