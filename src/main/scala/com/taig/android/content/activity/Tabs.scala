package com.taig.android.content.activity

import android.os.Bundle
import android.support.v4.view.ViewPager.SimpleOnPageChangeListener
import android.util.TypedValue.{COMPLEX_UNIT_DIP, COMPLEX_UNIT_SP, applyDimension}
import android.view.ViewGroup
import com.astuetz.PagerSlidingTabStrip
import com.taig.android._
import com.taig.android.content._
import com.taig.android.widget._

trait	Tabs
extends	Activity
with	Fragment
with	Pager
{
	def tabs: Tabs.Property[Tabs]

	override val pager = new Property[Tabs]( this ) with Tabs.Property.Pager[Tabs] 

	override def onCreate( state: Bundle )
	{
		require( tabs.titles.length == fragment.all.length, "Amount of fragments must equal amount of tab titles" )

		super.onCreate( state )

		pager.widget.setSwipeEnabled( true )
		addHeaderView( tabs.widget )
	}

	protected class Adapter extends super.Adapter
	{
		override def getPageTitle( position: Int ) = tabs.titles( position )
	}
}

object Tabs
{
	trait	Property[+T <: Tabs]
	extends	content.Property[T]
	{
		def titles: Seq[String]

		lazy val widget = new PagerSlidingTabStrip( content )
		{
			// TODO Apply colors from theme or actionbar
			//		setBackgroundResource( R.color.highlight )
			//		setIndicatorColorResource( R.color.neutral )
			setIndicatorHeight( applyDimension( COMPLEX_UNIT_DIP, 2, getResources.getDisplayMetrics ).toInt )
			setDividerColorResource( android.R.color.transparent )
			setPadding( R.dimen.spacing_x0_5.asPixel, 0 )
			setShouldExpand( true )
			setTabBackground( android.R.color.transparent )
			setTabPaddingLeftRight( R.dimen.spacing_x0_5.asPixel )
			setTextSize( applyDimension( COMPLEX_UNIT_SP, 12, getResources.getDisplayMetrics ).toInt )
			setUnderlineHeight( 0 )
			setViewPager( content.pager.widget )

			setOnPageChangeListener( new SimpleOnPageChangeListener
			{
				// Initial trigger
				onPageSelected( content.pager.widget.getCurrentItem )

				override def onPageSelected( position: Int )
				{
					val root = getChildAt( 0 ).asInstanceOf[ViewGroup]

					( 0 to root.getChildCount - 1 )
						.map( i => ( i, root.getChildAt( i ) ) )
						.foreach
					{
						case ( i, view ) if i == position => view.setAlpha( 1 )
						case ( _, view ) => view.setAlpha( 0.5f )
					}
				}
			} )
		}
	}

	object Property
	{
		trait	Pager[+T <: Tabs]
		extends	Pager.Property[T]
		{
			override lazy val adapter = new content.Adapter
		}
	}
}