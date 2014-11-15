package com.taig.android.content.fragment

import android.os.Bundle
import android.view.View
import android.widget.{ListView, TextView}
import com.taig.android._
import com.taig.android.content.{Property, Fragment}

trait	List
extends	com.taig.android.content.Fragment.List
with	Fragment
with	Layout
{
	def list: List.Property[List]

	override val layout = new Property( this ) with List.Property.Layout[List]

	override def onViewCreated( view: View, state: Bundle )
	{
		super.onViewCreated( view, state )

		list.empty.setText( list.message )
	}
}

object List
{
	trait	Property[+L <: List]
	extends	content.Property[L]
	{
		lazy val empty = content.findViewById( android.R.id.empty ).asInstanceOf[TextView]

		def message: String

		lazy val list = content.findViewById( android.R.id.list ).asInstanceOf[ListView]
	}

	object Property
	{
		trait Layout[+L <: List]
		extends	Layout.Property[L]
		{
			override val reference: Either[Int, View] = R.layout.list
		}
	}
}