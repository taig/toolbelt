package com.taig.android.content

import android.os.Bundle
import android.support.v7.app.ActionBarActivity
import android.view.{View, ViewGroup}
import com.taig.android._
import com.taig.android.conversion._

trait	Activity
extends	ActionBarActivity
with	Contextual
{
	override implicit def context = this

	override def onCreate( state: Bundle )
	{
		super.onCreate( state )

		setRootView( R.layout.main )

		// Adjust content margins to not be hidden behind the actionbar once the layout is done
		val params = findViewById( R.id.content ).getLayoutParams.asInstanceOf[ViewGroup.MarginLayoutParams]
		val header = findViewById( R.id.wrapper_header )
		val footer = findViewById( R.id.wrapper_footer )

		header.addOnLayoutChangeListener( ( view: View ) => params.topMargin = view.getHeight - header.getPaddingBottom )
		footer.addOnLayoutChangeListener( ( view: View ) => params.bottomMargin = view.getHeight - footer.getPaddingTop )
	}

	def setRootView( resource: Int ): Unit = setRootView( getLayoutInflater.inflate( resource, null ) )

	def setRootView( view: View ): Unit =
	{
		// Get the current root view
		val root = findViewById( R.id.root )

		// All hail the new root
		super.setContentView( view )

		if( view.findViewById( R.id.root ) == null )
		{
			view.setId( R.id.root )
		}

		if( root != null )
		{
			// Current root is no longer root
			root.setId( View.NO_ID )
			// But it's a child now!
			addContentView( root )
		}
	}

	def addHeaderView( resource: Int ): Unit = addHeaderView( getLayoutInflater.inflate( resource, null ) )

	def addHeaderView( view: View ): Unit =
	{
		findViewById( R.id.wrapper_header ).setVisibility( View.VISIBLE )
		addView( R.id.header, view )
	}

	def setHeaderView( resource: Int ): Unit = setHeaderView( getLayoutInflater.inflate( resource, null ) )

	def setHeaderView( view: View ): Unit =
	{
		findViewById( R.id.wrapper_header ).setVisibility( View.VISIBLE )
		setView( R.id.header, view )
	}

	def addContentView( resource: Int ): Unit = addContentView( getLayoutInflater.inflate( resource, null ) )

	def addContentView( view: View ): Unit = addView( R.id.content, view )

	override def setContentView( resource: Int ): Unit = setContentView( getLayoutInflater.inflate( resource, null ) )

	override def setContentView( view: View ): Unit = setView( R.id.content, view )

	def addFooterView( resource: Int ): Unit = addFooterView( getLayoutInflater.inflate( resource, null ) )

	def addFooterView( view: View ): Unit =
	{
		findViewById( R.id.wrapper_footer ).setVisibility( View.VISIBLE )
		addView( R.id.footer, view )
	}

	def setFooterView( resource: Int ): Unit = setFooterView( getLayoutInflater.inflate( resource, null ) )

	def setFooterView( view: View ): Unit =
	{
		findViewById( R.id.wrapper_footer ).setVisibility( View.VISIBLE )
		setView( R.id.footer, view )
	}

	private def addView( id: Int, view: View )
	{
		val element = findViewById( id ).asInstanceOf[ViewGroup]

		if( view.findViewById( id ) != null )
		{
			element.setId( View.NO_ID )
		}

		element.addView( view )
	}

	private def setView( id: Int, view: View )
	{
		val element = findViewById( id ).asInstanceOf[ViewGroup]

		if( view.findViewById( id ) != null )
		{
			element.setId( View.NO_ID )
		}

		element.removeAllViews()
		element.addView( view )
	}
}