package io.taig.android.content.contract

import java.lang.reflect.InvocationTargetException

import android.os.Bundle
import android.view.View
import io.taig.android.content.fragment.Fragment

import scala.language.postfixOps

/**
 * A Fragment may be a Creditor, loosely forcing the hosting Activity to implement its contract
 */
trait Creditor[+C <: Contract] extends Fragment {
    private var target: Any = null

    /**
     * Identifier that is expected to host the contract implementation in the debtor Activity
     * 
     * E.g. <code>"fragment.problem.Request"</code> forces the Activity to implement:
     * {{{
     * object Contract {
     *     object fragment {
     *         object problem {
     *             object Request extends C { ... }
     *         }
     *     }
     * }
     * }}}
     */
    def contract: String = {
        val debtor = context.getClass.getName
        val base = debtor.substring( 0, debtor.lastIndexOf( "." ) )
        val creditor = getClass.getName
        val nested = creditor.substring( 0, creditor.lastIndexOf( "." ) )

        val path: Option[String] = if ( nested.startsWith( base ) && base != nested ) {
            Some( nested.substring( base.length + 1 ) )
        } else {
            None
        }

        s"Contract.${path.map( _ + "." ).getOrElse( "" )}${getClass.getSimpleName}"
    }

    override def onAttach( activity: android.app.Activity ) = {
        super.onAttach( activity )

        try {
            target = contract.split( "\\." ).foldLeft[Any]( activity ) {
                case ( obj, name ) ⇒
                    val method = obj.getClass.getDeclaredMethod( name )
                    method.setAccessible( true )
                    method.invoke( obj )
            }.asInstanceOf[C]
        } catch {
            case _: NoSuchMethodException |
                _: IllegalAccessException |
                _: IllegalArgumentException |
                _: InvocationTargetException |
                _: ClassCastException ⇒
                throw new IllegalStateException(
                    s"Activity ${activity.getClass.getName} did not properly implement contract $contract"
                )
        }
    }

    override def onViewCreated( view: View, state: Option[Bundle] ) = {
        super.onViewCreated( view, state )

        ->> onViewCreated
    }

    override def onStart() = {
        super.onStart()

        ->> onStart
    }

    override def onResume() = {
        super.onResume()

        ->> onResume
    }

    override def onStop() = {
        super.onStop()

        ->> onStop
    }

    override def onDetach() = {
        super.onDetach()

        this.target = null
    }

    def ->> : C = target.asInstanceOf[C]
}