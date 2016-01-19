package io.taig.android.content.activity

import android.app
import android.app.TaskStackBuilder
import android.content.Intent
import android.content.res.Configuration
import android.graphics.{ Bitmap, Canvas }
import android.os.Bundle
import android.view.ActionMode.Callback
import android.view.ContextMenu.ContextMenuInfo
import android.view.WindowManager.LayoutParams
import android.view._
import io.taig.android.util.Log

trait Lifecycle extends Activity {
    override def onCreate( state: Option[Bundle] ): Unit = {
        Log.d( s"onCreate( $state )" )

        super.onCreate( state )
    }

    override def onCreateOptionsMenu( menu: Menu ) = {
        Log.d( s"onCreateOptionsMenu( $menu )" )

        super.onCreateOptionsMenu( menu )
    }

    override protected def onPostCreate( state: Option[Bundle] ): Unit = {
        super.onPostCreate( state )

        Log.d( s"onPostCreate( $state )" )
    }

    override def onConfigurationChanged( configuration: Configuration ): Unit = {
        Log.d( s"onConfigurationChanged( $configuration )" )

        super.onConfigurationChanged( configuration )
    }

    override def onStop(): Unit = {
        Log.d( s"onStop()" )

        super.onStop()
    }

    override def onPostResume(): Unit = {
        Log.d( s"onPostResume()" )

        super.onPostResume()
    }

    override def onCreatePanelView( id: Int ) = {
        Log.d( s"onCreatePanelView( $id )" )

        super.onCreatePanelView( id )
    }

    override def onDestroy(): Unit = {
        Log.d( s"onDestroy()" )

        super.onDestroy()
    }

    override def onTitleChanged( title: CharSequence, color: Int ): Unit = {
        Log.d( s"onTitleChanged( $title, $color )" )

        super.onTitleChanged( title, color )
    }

    override def onCreatePanelMenu( id: Int, menu: Menu ) = {
        Log.d( s"onCreatePanelMenu( $id, $menu )" )

        super.onCreatePanelMenu( id, menu )
    }

    override def onPreparePanel( id: Int, view: View, menu: Menu ) = {
        Log.d( s"onPreparePanel( $id, $view, $menu )" )

        super.onPreparePanel( id, view, menu )
    }

    override def onPanelClosed( id: Int, menu: Menu ): Unit = {
        Log.d( s"onPanelClosed( $id, $menu )" )

        super.onPanelClosed( id, menu )
    }

    override def onMenuOpened( id: Int, menu: Menu ) = {
        Log.d( s"onMenuOpened( $id, $menu )" )

        super.onMenuOpened( id, menu )
    }

    override def onBackPressed(): Unit = {
        Log.d( s"onBackPressed()" )

        super.onBackPressed()
    }

    override def onKeyShortcut( key: Int, event: KeyEvent ) = {
        Log.d( s"onKeyShortcut( $key, $event )" )

        super.onKeyShortcut( key, event )
    }

    override def onKeyDown( key: Int, event: KeyEvent ) = {
        Log.d( s"onKeyDown( $key, $event )" )

        super.onKeyDown( key, event )
    }

    override def onActivityResult( request: Int, result: Int, data: Intent ): Unit = {
        Log.d( s"onActivityResult( $request, $result, $data )" )

        super.onActivityResult( request, result, data )
    }

    override def onLowMemory(): Unit = {
        Log.d( s"onLowMemory()" )

        super.onLowMemory()
    }

    override def onPause(): Unit = {
        Log.d( s"onPause()" )

        super.onPause()
    }

    override def onNewIntent( intent: Intent ): Unit = {
        Log.d( s"onNewIntent( $intent )" )

        super.onNewIntent( intent )
    }

    override def onResume(): Unit = {
        Log.d( s"onResume()" )

        super.onResume()
    }

    override def onSaveInstanceState( state: Bundle ): Unit = {
        Log.d( s"onSaveInstanceState( $state )" )

        super.onSaveInstanceState( state )
    }

    override def onStart(): Unit = {
        Log.d( s"onStart()" )

        super.onStart()
    }

    override def onAttachFragment( fragment: android.app.Fragment ): Unit = {
        Log.d( s"onAttachFragment( $fragment )" )

        super.onAttachFragment( fragment )
    }

    override def onRestoreInstanceState( state: Bundle ): Unit = {
        Log.d( s"onRestoreInstanceState( $state )" )

        super.onRestoreInstanceState( state )
    }

    override def onRestart(): Unit = {
        Log.d( s"onRestart()" )

        super.onRestart()
    }

    override def onUserLeaveHint(): Unit = {
        Log.d( s"onUserLeaveHint()" )

        super.onUserLeaveHint()
    }

    override def onCreateThumbnail( bitmap: Bitmap, canvas: Canvas ) = {
        Log.d( s"onCreateThumbnail( $bitmap, $canvas )" )

        super.onCreateThumbnail( bitmap, canvas )
    }

    override def onCreateDescription() = {
        Log.d( s"onCreateDescription()" )

        super.onCreateDescription()
    }

    override def onProvideAssistData( data: Bundle ): Unit = {
        Log.d( s"onProvideAssistData( $data )" )

        super.onProvideAssistData( data )
    }

    override def onKeyLongPress( key: Int, event: KeyEvent ) = {
        Log.d( s"onKeyLongPress( $key, $event )" )

        super.onKeyLongPress( key, event )
    }

    override def onKeyUp( key: Int, event: KeyEvent ) = {
        Log.d( s"onKeyUp( $key, $event )" )

        super.onKeyUp( key, event )
    }

    override def onKeyMultiple( key: Int, repeatCount: Int, event: KeyEvent ) = {
        Log.d( s"onKeyMultiple( $key, $repeatCount, $event )" )

        super.onKeyMultiple( key, repeatCount, event )
    }

    override def onTouchEvent( event: MotionEvent ) = {
        Log.d( s"onTouchEvent( $event )" )

        super.onTouchEvent( event )
    }

    override def onTrackballEvent( event: MotionEvent ) = {
        Log.d( s"onTrackballEvent( $event )" )

        super.onTrackballEvent( event )
    }

    override def onGenericMotionEvent( event: MotionEvent ) = {
        Log.d( s"onGenericMotionEvent( $event )" )

        super.onGenericMotionEvent( event )
    }

    override def onUserInteraction(): Unit = {
        Log.d( s"onUserInteraction()" )

        super.onUserInteraction()
    }

    override def onWindowAttributesChanged( parameters: LayoutParams ): Unit = {
        Log.d( s"onWindowAttributesChanged( $parameters )" )

        super.onWindowAttributesChanged( parameters )
    }

    override def onWindowFocusChanged( hasFocus: Boolean ): Unit = {
        Log.d( s"onWindowFocusChanged( $hasFocus )" )

        super.onWindowFocusChanged( hasFocus )
    }

    override def onAttachedToWindow(): Unit = {
        Log.d( s"onAttachedToWindow()" )

        super.onAttachedToWindow()
    }

    override def onDetachedFromWindow(): Unit = {
        Log.d( s"onDetachedFromWindow()" )

        super.onDetachedFromWindow()
    }

    override def onOptionsItemSelected( item: MenuItem ) = {
        Log.d( s"onOptionsItemSelected( $item )" )

        super.onOptionsItemSelected( item )
    }

    override def onPrepareOptionsMenu( menu: Menu ) = {
        Log.d( s"onPrepareOptionsMenu( $menu )" )

        super.onPrepareOptionsMenu( menu )
    }

    override def onNavigateUp() = {
        Log.d( s"onNavigateUp()" )

        super.onNavigateUp()
    }

    override def onNavigateUpFromChild( child: app.Activity ) = {
        Log.d( s"onNavigateUpFromChild( $child )" )

        super.onNavigateUpFromChild( child )
    }

    override def onCreateNavigateUpTaskStack( builder: TaskStackBuilder ): Unit = {
        Log.d( s"onCreateNavigateUpTaskStack( $builder )" )

        super.onCreateNavigateUpTaskStack( builder )
    }

    override def onPrepareNavigateUpTaskStack( builder: TaskStackBuilder ): Unit = {
        Log.d( s"onPrepareNavigateUpTaskStack( $builder )" )

        super.onPrepareNavigateUpTaskStack( builder )
    }

    override def onOptionsMenuClosed( menu: Menu ): Unit = {
        Log.d( s"onOptionsMenuClosed( $menu )" )

        super.onOptionsMenuClosed( menu )
    }

    override def onCreateContextMenu( menu: ContextMenu, view: View, info: ContextMenuInfo ): Unit = {
        Log.d( s"onCreateContextMenu( $menu, $view, $info )" )

        super.onCreateContextMenu( menu, view, info )
    }

    override def onContextItemSelected( item: MenuItem ) = {
        Log.d( s"onContextItemSelected( $item )" )

        super.onContextItemSelected( item )
    }

    override def onContextMenuClosed( menu: Menu ): Unit = {
        Log.d( s"onContextMenuClosed( $menu )" )

        super.onContextMenuClosed( menu )
    }

    override def onSearchRequested() = {
        Log.d( s"onSearchRequested()" )

        super.onSearchRequested()
    }

    override def onActivityReenter( result: Int, data: Intent ): Unit = {
        Log.d( s"onActivityReenter( $result, $data )" )

        super.onActivityReenter( result, data )
    }

    override def onChildTitleChanged( child: app.Activity, title: CharSequence ): Unit = {
        Log.d( s"onChildTitleChanged( $child, $title )" )

        super.onChildTitleChanged( child, title )
    }

    override def onVisibleBehindCanceled(): Unit = {
        Log.d( s"onVisibleBehindCanceled()" )

        super.onVisibleBehindCanceled()
    }

    override def onEnterAnimationComplete(): Unit = {
        Log.d( s"onEnterAnimationComplete()" )

        super.onEnterAnimationComplete()
    }

    override def onWindowStartingActionMode( callback: Callback ) = {
        Log.d( s"onWindowStartingActionMode( $callback )" )

        super.onWindowStartingActionMode( callback )
    }

    override def onActionModeStarted( mode: ActionMode ): Unit = {
        Log.d( s"onActionModeStarted( $mode )" )

        super.onActionModeStarted( mode )
    }

    override def onActionModeFinished( mode: ActionMode ): Unit = {
        Log.d( s"onActionModeFinished( $mode )" )

        super.onActionModeFinished( mode )
    }
}