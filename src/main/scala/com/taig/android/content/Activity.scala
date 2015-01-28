package com.taig.android.content

import android.os.Bundle
import android.support.v7.app.ActionBarActivity
import android.view.ViewGroup.LayoutParams
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.{Window, Menu, View, ViewGroup}
import com.taig.android._
import com.taig.android.conversion._

trait	Activity
extends	ActionBarActivity
with	Contextual
{
	override implicit def context = this

	private var root: View = null

	override final def onCreate( state: Bundle )
	{
		super.onCreate( state )

		onCreate( Option( state ) )

		if( !getWindow.hasFeature( Window.FEATURE_ACTION_BAR_OVERLAY ) )
		{
			// Adjust content margins to not be hidden behind the actionbar once the layout is done
			val params = findViewById( R.id.content ).getLayoutParams.asInstanceOf[ViewGroup.MarginLayoutParams]
			val header = findViewById( R.id.wrapper_header )
			val footer = findViewById( R.id.wrapper_footer )
	
			header.addOnLayoutChangeListener( ( view: View ) => params.topMargin = view.getHeight - header.getPaddingBottom )
			footer.addOnLayoutChangeListener( ( view: View ) => params.bottomMargin = view.getHeight - footer.getPaddingTop )
		}
	}

	def onCreate( state: Option[Bundle] )
	{
		setRootView( R.layout.main )
	}

	override def onPostCreate( savedInstanceState: Bundle ) = super.onPostCreate( savedInstanceState )

	override def onCreateOptionsMenu( menu: Menu ) =
	{
		// This is so hacky. Shame on your fucked up lifecycle, Android :/
		// There appears to be an issue with the ViewPager that leads to multiple calls of this method and
		// therefore multiple menu inflations. To prevent this before creating the menu all elements are cleared.
		// Even worse: this seems to be a race condition related issue that does not appear all of the times.
		// Further discussion: https://code.google.com/p/android/issues/detail?id=29472
		this match
		{
			case activity: activity.Options => activity.options.clear()
			case _ => // Noting to do
		}

		this match
		{
			case activity: activity.Fragment =>
			{
				activity.fragment.getActive() match
				{
					case fragment: fragment.Options => fragment.options.clear()
					case _ => // Nothing to do
				}
			}
			case _ => // Nothing to do?
		}

		this match
		{
			case activity: activity.Options => activity.options.inflate()
			case _ => // Nothing to do
		}

		this match
		{
			case activity: activity.Fragment =>
			{
				activity.fragment.getActive() match
				{
					case fragment: fragment.Options => fragment.options.inflate()
					case _ => // Nothing to do
				}
			}
			case _ => // Nothing to do?
		}

		super.onCreateOptionsMenu( menu )
	}

	override def supportInvalidateOptionsMenu()
	{
		super.supportInvalidateOptionsMenu()

		this match
		{
			case activity: activity.Options => activity.options.clear()
			case _ => // Nothing to do
		}

		this match
		{
			case activity: activity.Fragment =>
			{
				activity.fragment.getActive() match
				{
					case fragment: fragment.Options => fragment.options.clear()
					case _ => // Nothing to do
				}
			}
			case _ => // Nothing to do?
		}
	}

	def setRootView( resource: Int ): Unit = setRootView( getLayoutInflater.inflate( resource, null ) )

	def setRootView( view: View ): Unit =
	{
		super.setContentView( view )

		if( root != null )
		{
			addContentView( root )
		}

		root = view
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
		setView( R.id.header, view, None )
	}

	def addContentView( resource: Int ): Unit = addContentView( getLayoutInflater.inflate( resource, null ) )

	def addContentView( view: View ): Unit = addView( R.id.content, view )

	override def setContentView( resource: Int ): Unit = setContentView( getLayoutInflater.inflate( resource, null ) )

	override def setContentView( view: View ): Unit =
	{
		setContentView( view, new LayoutParams( MATCH_PARENT, MATCH_PARENT ) )
	}

	override def setContentView( view: View, params: LayoutParams ): Unit =
	{
		setView( R.id.content, view, Option( params ) )
	}

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
		setView( R.id.footer, view, None )
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

	private def setView( id: Int, view: View, params: Option[LayoutParams] )
	{
		val element = findViewById( id ).asInstanceOf[ViewGroup]

		if( view.findViewById( id ) != null )
		{
			element.setId( View.NO_ID )
		}

		element.removeAllViews()

		params match
		{
			case Some( params ) => element.addView( view, params )
			case None => element.addView( view )
		}
	}
}