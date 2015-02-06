package com.taig.android.widget

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.util.{Log, AttributeSet}
import android.view.ViewGroup
import com.astuetz.PagerSlidingTabStrip
import com.astuetz.PagerSlidingTabStrip.CustomTabProvider
import com.taig.android.R
import com.taig.android.content.{Contextual, _}

class	Tabs( val attributes: AttributeSet = null, val style: Int = 0 )( implicit context: Context )
extends	PagerSlidingTabStrip( context, attributes, style )
with	Widget.Styleable
{
	def this( context: Context, attributes: AttributeSet, style: Int ) = this( attributes, style )( context )

	def this( context: Context, attributes: AttributeSet ) = this( attributes )( context )

	def this( context: Context ) = this()( context )
}

object Tabs
{
	trait	Adapter
	extends	PagerAdapter
	with	CustomTabProvider
	with	Contextual
	{
		override def getCustomTabView( view: ViewGroup, i: Int ) =
		{
			context.Inflater.inflate( R.layout.tab, view, false )
		}
	}
}