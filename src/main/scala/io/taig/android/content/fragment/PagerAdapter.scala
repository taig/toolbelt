package io.taig.android.content.fragment

import android.app.{ Fragment, FragmentManager, FragmentTransaction }
import android.os.Parcelable
import android.support.v13.app.FragmentCompat
import android.view.{ View, ViewGroup }

/**
 * An alternative FragmentPagerAdapter implementation, that allows to access the managed fragemnts
 */
abstract class PagerAdapter( manager: FragmentManager ) extends android.support.v4.view.PagerAdapter {
    private var transaction: Option[FragmentTransaction] = None

    private var primary: Fragment = null

    private def getOrCreateTransaction = transaction match {
        case Some( transaction ) ⇒ transaction
        case None ⇒
            val transaction = manager.beginTransaction()
            this.transaction = Some( transaction )
            transaction
    }

    def create( position: Int ): Fragment

    def get( position: Int ): Fragment = manager.findFragmentByTag( identifier( position ) )

    override def instantiateItem( container: ViewGroup, position: Int ) = {
        val tag = identifier( position )

        val fragment = Option( get( position ) ) match {
            case Some( fragment ) ⇒
                getOrCreateTransaction.attach( fragment )
                fragment
            case None ⇒
                val fragment = create( position )
                getOrCreateTransaction.add( container.getId, fragment, tag )
                fragment
        }

        if ( fragment != primary ) {
            FragmentCompat.setMenuVisibility( fragment, false )
        }

        fragment
    }

    override def destroyItem( container: ViewGroup, position: Int, `object`: Any ) = {
        getOrCreateTransaction.detach( `object`.asInstanceOf[Fragment] )
    }

    override def setPrimaryItem( container: ViewGroup, position: Int, `object`: Any ) = {
        val fragment = `object`.asInstanceOf[Fragment]

        if ( fragment != primary ) {
            if ( primary != null ) {
                FragmentCompat.setMenuVisibility( primary, false )
            }

            if ( fragment != null ) {
                FragmentCompat.setMenuVisibility( fragment, true )
            }

            primary = fragment
        }
    }

    def getPrimaryItem = primary

    override def finishUpdate( container: ViewGroup ) = transaction.foreach( transaction ⇒ {
        transaction.commitAllowingStateLoss()
        this.transaction = None
        manager.executePendingTransactions()
    } )

    override def isViewFromObject( view: View, o: Any ) = o match {
        case fragment: Fragment ⇒ fragment.getView == view
        case _                  ⇒ false
    }

    def identifier( index: Int ) = "taig:switcher:" + getClass.getName + ":" + index

    override def saveState() = null

    override def restoreState( state: Parcelable, loader: ClassLoader ) = {}
}