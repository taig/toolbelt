package io.taig.android.monix.app.fragment

import android.app.{ Activity, FragmentManager }
import android.os.Bundle
import io.taig.android.app.fragment.Fragment
import io.taig.android.monix.app.fragment.Reactive.Event
import monix.reactive.observers.Subscriber

trait Reactive extends Fragment {
    var background: Reactive.Helper = null

    @inline
    def trigger( event: Event ): Unit = background.downstream.onNext( event )

    override def onAttach( activity: Activity ): Unit = {
        super.onAttach( activity )

        background = getFragmentManager
            .findFragmentByTag( s"$getTag-helper" )
            .asInstanceOf[Reactive.Helper]

        trigger( Event.Attach( activity ) )
    }

    override def onCreate( state: Option[Bundle] ): Unit = {
        super.onCreate( state )

        trigger( Event.Create( state ) )
    }

    override def onActivityCreated( state: Option[Bundle] ): Unit = {
        super.onActivityCreated( state )

        trigger( Event.ActivityCreated( state ) )
    }

    override def onStart(): Unit = {
        super.onStart()

        trigger( Event.Start )
    }

    override def onResume(): Unit = {
        super.onResume()

        trigger( Event.Resume )
    }

    override def onPause(): Unit = {
        super.onPause()

        trigger( Event.Pause )
    }

    override def onStop(): Unit = {
        super.onStop()

        trigger( Event.Stop )
    }

    override def onDestroy(): Unit = {
        super.onDestroy()

        trigger( Event.Destroy )
    }

    override def onDetach(): Unit = {
        super.onDetach()

        trigger( Event.Detach )

        if ( getActivity.isFinishing ) {
            background.downstream.onComplete()
        }
    }
}

object Reactive {
    sealed trait Event

    object Event {
        case class Action( name: String ) extends Event
        case class ActivityCreated( state: Option[Bundle] ) extends Event
        case class Attach( activity: Activity ) extends Event
        case class Create( state: Option[Bundle] ) extends Event
        case object Destroy extends Event
        case object Detach extends Event
        case object Pause extends Event
        case object Resume extends Event
        case object Start extends Event
        case object Stop extends Event
    }

    class Helper extends Fragment {
        var downstream: Subscriber.Sync[Event] = null

        var fragment: Reactive = null

        var manager: FragmentManager = null

        var tag: String = null

        override def onCreate( state: Option[Bundle] ): Unit = {
            super.onCreate( state )

            setRetainInstance( true )

            manager.beginTransaction
                .add( fragment, tag )
                .commit()

            manager = null
            fragment = null
        }
    }

    object Helper {
        def apply(
            downstream: Subscriber.Sync[Event],
            fragment:   Reactive,
            manager:    FragmentManager,
            tag:        String
        ): Helper = {
            val helper = new Helper

            helper.downstream = downstream
            helper.fragment = fragment
            helper.manager = manager
            helper.tag = tag
            helper
        }
    }
}