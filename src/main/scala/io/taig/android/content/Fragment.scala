package io.taig.android.content

import android.os.Bundle
import android.view.{ LayoutInflater, View, ViewGroup }

trait Fragment
        extends android.app.Fragment
        with Contextual {
    implicit override def context = getActivity

    override final def onCreate( state: Bundle ) = {
        super.onCreate( state )

        onCreate( Option( state ) )
    }

    def onCreate( state: Option[Bundle] ): Unit = {}

    override final def onActivityCreated( state: Bundle ) = {
        super.onActivityCreated( state )

        onActivityCreated( Option( state ) )
    }

    def onActivityCreated( state: Option[Bundle] ): Unit = {}

    override final def onCreateView( inflater: LayoutInflater, container: ViewGroup, state: Bundle ) = {
        onCreateView( inflater, Option( container ), Option( state ) )
    }

    def onCreateView( inflater: LayoutInflater, container: Option[ViewGroup], state: Option[Bundle] ): View = null

    override final def onViewCreated( view: View, state: Bundle ) {
        super.onViewCreated( view, state )

        onViewCreated( view, Option( state ) )
    }

    def onViewCreated( view: View, state: Option[Bundle] ): Unit = {}
}