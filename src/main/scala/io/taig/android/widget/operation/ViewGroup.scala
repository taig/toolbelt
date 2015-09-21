package io.taig.android.widget.operation

abstract class ViewGroup( viewGroup: android.view.ViewGroup ) {
    /**
     * Recursively discovers all children of this view and flattens them into a one dimensional collection in no
     * particular order
     */
    def children = {
        def discover( view: android.view.View ): Seq[android.view.View] = view match {
            case viewGroup: android.view.ViewGroup ⇒
                ( 0 to viewGroup.getChildCount - 1 )
                    .map( viewGroup.getChildAt )
                    .flatMap( discover ) :+ view
            case _ ⇒ Seq( view )
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
    def prependView( view: android.view.View, parameters: android.view.ViewGroup.LayoutParams ) = {
        viewGroup.addView( view, 0, parameters )
    }

    /**
     * Add view as last child
     */
    def appendView( view: android.view.View ) = viewGroup.addView( view, viewGroup.getChildCount )

    /**
     * Add view as last child
     */
    def appendView( view: android.view.View, parameters: android.view.ViewGroup.LayoutParams ) = {
        viewGroup.addView( view, viewGroup.getChildCount, parameters )
    }
}