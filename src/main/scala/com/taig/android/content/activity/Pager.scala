package com.taig.android.content.activity

import android.os.Bundle
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.{app => support}
import android.view.ViewGroup
import com.taig.android._
import com.taig.android.content.Activity
import com.taig.android.content.Property

trait	Pager
extends	Activity
with	Fragment
{
	protected implicit def `Seq[Class[Fragment]] -> Pager.Fragment`( fragments: Seq[Class[_ <: support.Fragment]] ): Pager.Fragment =
	{
		new Property( this ) with Pager.Fragment
		{
			override def all = fragments
		}
	}

	val pager = new Property( this ) with Pager.Property

	override def fragment: Pager.Fragment

	override def onCreate( state: Bundle )
	{
		super.onCreate( state )

		setContentView( R.layout.pager )

		pager.widget.setAdapter( pager.adapter )
	}

	protected class Adapter extends FragmentPagerAdapter( getSupportFragmentManager )
	{
		protected[Pager] var current: support.Fragment = null

		override def getItem( position: Int ) = fragment.instantiate( fragment.all( position ) )

		override def getCount = fragment.all.length

		override def setPrimaryItem( container: ViewGroup, position: Int, `object`: Any )
		{
			this.current = `object` match
			{
				case fragment: support.Fragment => fragment
				case _ => null
			}

			super.setPrimaryItem( container, position, `object` )
		}
	}
}

object Pager
{
	trait	Property
	extends	content.Property[Pager]
	{
		lazy val widget = content.findViewById( R.id.pager ).asInstanceOf[com.taig.android.widget.Pager]

		lazy val adapter = new content.Adapter

		def next() = widget.setCurrentItem( widget.getCurrentItem + 1 )

		def previous() = widget.setCurrentItem( widget.getCurrentItem - 1 )

		def jump( fragment: Fragment ): Unit = jump( content.fragment.all.indexOf( fragment ) )

		def jump( position: Int ): Unit = widget.setCurrentItem( position )
	}

	trait	Fragment
	extends	content.Property[Pager]
	with	content.activity.Fragment.Property
	{
		override def getActive() = content.pager.adapter.current
	}
}