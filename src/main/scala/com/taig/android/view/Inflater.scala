package com.taig.android.view

import android.view.{LayoutInflater, View, ViewGroup}

case class Inflater[V <: View]( resource: Int, root: Option[ViewGroup] = None, attachToRoot: Boolean = false )( implicit inflater: LayoutInflater )
{
	val view = inflater.inflate( resource, root.orNull, attachToRoot ).asInstanceOf[V]
}