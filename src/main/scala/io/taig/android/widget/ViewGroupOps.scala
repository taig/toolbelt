package io.taig.android.widget

import android.view.{View, ViewGroup}

trait ViewGroupOps
{
	def viewGroup: ViewGroup

	/**
	 * Recursively discovers all children of this view and flattens them into a one dimensional collection in no
	 * particular order
	 */
	def children =
	{
		def discover( view: ViewGroup ): Seq[View] =
		{
			( 0 to view.getChildCount - 1 )
				.map( view.getChildAt ) match
			{
				case group: ViewGroup => group +: discover( group )
				case _ => Seq( view )
			}
		}

		discover( viewGroup )
	}

	/**
	 * Add view as first child
	 */
	def prependView( view: View ) = viewGroup.addView( view, 0 )

	/**
	 * Add view as first child
	 */
	def prependView( view: View, parameters: ViewGroup.LayoutParams ) = viewGroup.addView( view, 0, parameters )

	/**
	 * Add view as last child
	 */
	def appendView( view: View ) = viewGroup.addView( view, viewGroup.getChildCount - 1 )

	/**
	 * Add view as last child
	 */
	def appendView( view: View, parameters: ViewGroup.LayoutParams ) =
	{
		viewGroup.addView( view, viewGroup.getChildCount - 1, parameters )
	}
}