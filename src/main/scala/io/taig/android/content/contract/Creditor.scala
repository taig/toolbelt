package io.taig.android.content.contract

import android.os.Bundle
import android.view.View
import io.taig.android.content.fragment.Fragment
import io.taig.android.extension.util._

import scala.language.postfixOps
import scala.util.{ Success, Try }

/**
 * A Fragment may be a Creditor, loosely forcing the hosting Activity to implement its contract
 */
trait Creditor[+C <: Contract] extends Fragment {
    private var target: Option[Any] = None

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

        s"${path.map( _ + "." ).getOrElse( "" )}${getClass.getSimpleName}"
    }

    override def onAttach( activity: android.app.Activity ) = {
        super.onAttach( activity )

        target = {
            val namespace = contract.split( "\\." )

            val targets = activity
                .getClass
                .getDeclaredMethods
                .filter( _.getName.endsWith( "Contract" ) )
                .map( method ⇒ {
                    method.setAccessible( true )
                    method.invoke( activity )
                } )
                .map( contract ⇒ Try {
                    namespace.foldLeft( contract ) {
                        case ( contract, name ) ⇒
                            val method = contract.getClass.getDeclaredMethod( name )
                            method.setAccessible( true )
                            method.invoke( contract )
                    }
                } )
                .collect{ case Success( target ) ⇒ target }

            targets match {
                case _ if targets.size == 1 ⇒ Some( targets.head )
                case _ if targets.isEmpty   ⇒ None
                case _ ⇒ throw new IllegalStateException(
                    s"There are multiple valid contract implementations for $namespace in " +
                        activity.getClass.parents().map( _.getName ).mkString( ", " )
                )
            }
        }
    }

    override def onViewCreated( view: View, state: Option[Bundle] ) = {
        super.onViewCreated( view, state )

        ->? foreach ( _.onViewCreated )
    }

    override def onStart() = {
        super.onStart()

        ->? foreach ( _.onStart )
    }

    override def onResume() = {
        super.onResume()

        ->? foreach ( _.onResume )
    }

    override def onStop() = {
        super.onStop()

        ->? foreach ( _.onStop )
    }

    override def onDetach() = {
        super.onDetach()

        target = None
    }

    def ->> : C = ->? getOrElse {
        throw new IllegalStateException(
            s"Activity ${context.getClass.getName} did not properly implement contract Contract.$contract"
        )
    }

    def ->? : Option[C] = target.map( _.asInstanceOf[C] )
}