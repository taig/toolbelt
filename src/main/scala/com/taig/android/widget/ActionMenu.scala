package com.taig.android.widget

import android.content.Context
import android.support.v7.widget.ActionMenuView.OnMenuItemClickListener
import android.util.AttributeSet
import android.view.MenuItem

import scala.collection.mutable

class	ActionMenu( val attributes: AttributeSet = null )( implicit context: Context )
extends	android.support.v7.widget.ActionMenuView( context, attributes )
with	Widget
{
	def this( context: Context, attributes: AttributeSet ) = this( attributes )( context )

	def this( context: Context ) = this()( context )

	private val listeners = mutable.Buffer[OnMenuItemClickListener]()

	super.setOnMenuItemClickListener( new OnMenuItemClickListener
	{
		override def onMenuItemClick( item: MenuItem ) = listeners.map( _.onMenuItemClick( item ) ).contains( true )
	} )

	override final def setOnMenuItemClickListener( listener: OnMenuItemClickListener )
	{
		throw new RuntimeException( "Use addOnMenuItemClickListener" )
	}

	def addOnMenuItemClickListener( listener: OnMenuItemClickListener ) =
	{
		listeners.append( listener )
		this
	}
}