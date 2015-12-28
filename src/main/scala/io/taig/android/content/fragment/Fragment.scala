package io.taig.android.content.fragment

import android.os.Bundle
import android.view.{ LayoutInflater, View, ViewGroup }
import io.taig.android.content.Contextual

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

    def onCreateView( inflater: LayoutInflater, container: Option[ViewGroup], state: Option[Bundle] ): View = {
        super.onCreateView( inflater, container.orNull, state.orNull )
    }

    override final def onViewCreated( view: View, state: Bundle ) {
        super.onViewCreated( view, state )

        onViewCreated( view, Option( state ) )
    }

    def onViewCreated( view: View, state: Option[Bundle] ): Unit = {}
}