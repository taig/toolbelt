package io.taig.android.app.contract

import android.os.Bundle
import android.view.View
import io.taig.android.app.fragment.Fragment

import scala.language.postfixOps
import scala.util.{ Success, Try }

/**
 * A Fragment may be a Creditor, loosely forcing the hosting Activity to implement its contract
 */
trait Creditor[+C <: Contract] extends Fragment {
    private var targets = Seq.empty[Any]

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

        targets = {
            val namespace = contract.split( "\\." )

            activity
                .getClass
                .getDeclaredMethods
                .filter( _.getName.endsWith( "Contract" ) )
                .map { method ⇒
                    method.setAccessible( true )
                    method.invoke( activity )
                }
                .map { contract ⇒
                    Try {
                        namespace.foldLeft( contract ) {
                            case ( contract, name ) ⇒
                                val method = contract.getClass.getDeclaredMethod( name )
                                method.setAccessible( true )
                                method.invoke( contract )
                        }
                    }
                }
                .collect{ case Success( target ) ⇒ target }
        }
    }

    override def onViewCreated( view: View, state: Option[Bundle] ) = {
        super.onViewCreated( view, state )

        ->? { _ onViewCreated }
    }

    override def onStart() = {
        super.onStart()

        ->? { _.onStart }
    }

    override def onResume() = {
        super.onResume()

        ->? { _.onResume }
    }

    override def onStop() = {
        super.onStop()

        ->? { _.onStop }
    }

    override def onDetach() = {
        super.onDetach()

        targets = Seq.empty
    }

    def ->>( f: C ⇒ Any ): Unit = {
        if ( targets.isEmpty ) {
            throw new IllegalStateException(
                s"Activity ${context.getClass.getName} did not properly implement contract Contract.$contract"
            )
        }

        ->?( f )
    }

    def ->?( f: C ⇒ Any ): Unit = targets.foreach( target ⇒ f( target.asInstanceOf[C] ) )
}