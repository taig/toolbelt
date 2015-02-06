package com.taig.android.content.activity

import android.os.Bundle
import com.taig.android._
import com.taig.android.content._

trait	Tabs
extends	Activity
with	Fragment
with	Pager
{
	protected implicit def `Seq[String] -> Tabs.Property`( strings: Seq[String] ) =
	{
		new Property( this ) with Tabs.Property
		{
			override def titles = strings
		}
	}

	def tabs: Tabs.Property

	override val pager = new Property( this ) with Tabs.Pager 

	override def onCreate( state: Option[Bundle] )
	{
		require( tabs.titles.length == fragment.all.length, "Amount of fragments must equal amount of tab titles" )

		super.onCreate( state )

		pager.widget.setSwipeEnabled( true )
		tabs.widget.setViewPager( pager.widget )
		addHeaderView( tabs.widget )
	}
}

object Tabs
{
	trait	Property
	extends	content.Property[Tabs]
	{
		def titles: Seq[String]

		lazy val widget = context.Inflater.inflate( R.layout.tabs, null ).asInstanceOf[com.taig.android.widget.Tabs]
	}

	trait	Pager
	extends	content.Property[Tabs]
	with	Pager.Property
	{
		override lazy val adapter = new Adapter( content )
	}

	class	Adapter( activity: Tabs )
	extends	Pager.Adapter( activity )
	with	widget.Tabs.Adapter
	{
		override implicit def context = activity.context

		override def getPageTitle( position: Int ) = activity.tabs.titles( position )
	}
}