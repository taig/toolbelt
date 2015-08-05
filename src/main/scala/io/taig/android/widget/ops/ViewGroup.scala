package io.taig.android.widget.ops

trait ViewGroup
{
	def viewGroup: android.view.ViewGroup

	/**
	 * Recursively discovers all children of this view and flattens them into a one dimensional collection in no
	 * particular order
	 */
	def children =
	{
		def discover( view: android.view.ViewGroup ): Seq[android.view.View] =
		{
			( 0 to view.getChildCount - 1 )
				.map( view.getChildAt ) match
			{
				case group: android.view.ViewGroup => group +: discover( group )
				case _ => Seq( view )
			}
		}

		discover( viewGroup )
	}

	/**
	 * Add view as first child
	 */
	def prependView( view: android.view.View ) = viewGroup.addView( view, 0 )

	/**
	 * Add view as first child
	 */
	def prependView( view: android.view.View, parameters: android.view.ViewGroup.LayoutParams ) =
	{
		viewGroup.addView( view, 0, parameters )
	}

	/**
	 * Add view as last child
	 */
	def appendView( view: android.view.View ) = viewGroup.addView( view, viewGroup.getChildCount )

	/**
	 * Add view as last child
	 */
	def appendView( view: android.view.View, parameters: android.view.ViewGroup.LayoutParams ) =
	{
		viewGroup.addView( view, viewGroup.getChildCount, parameters )
	}
}
