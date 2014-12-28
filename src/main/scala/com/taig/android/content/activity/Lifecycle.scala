package com.taig.android.content.activity

import android.app
import android.app.TaskStackBuilder
import android.content.res.Configuration
import android.content.{Context, Intent}
import android.graphics.{Bitmap, Canvas}
import android.os.Bundle
import android.support.v4
import android.support.v4.{app => support}
import android.support.v7.view
import android.util.{AttributeSet, Log}
import android.view.ActionMode.Callback
import android.view.ContextMenu.ContextMenuInfo
import android.view.WindowManager.LayoutParams
import android.view._
import com.taig.android.content.Activity

trait	Lifecycle
extends	Activity
{
	override def onCreate( state: Option[Bundle] )
	{
		Log.d( getClass.getName, s"onCreate( $state )" )

		super.onCreate( state )
	}

	override def onCreateOptionsMenu( menu: Menu ) =
	{
		Log.d( getClass.getName, s"onCreateOptionsMenu( $menu )" )

		super.onCreateOptionsMenu( menu )
	}

	override def supportInvalidateOptionsMenu()
	{
		Log.d( getClass.getName, s"supportInvalidateOptionsMenu()" )

		super.supportInvalidateOptionsMenu()
	}

	override def onConfigurationChanged( configuration: Configuration )
	{
		Log.d( getClass.getName, s"onConfigurationChanged( $configuration )")

		super.onConfigurationChanged( configuration )
	}

	override def onStop()
	{
		Log.d( getClass.getName, s"onStop()")

		super.onStop()
	}

	override def onPostResume()
	{
		Log.d( getClass.getName, s"onPostResume()")

		super.onPostResume()
	}

	override def onCreatePanelView( id: Int ) =
	{
		Log.d( getClass.getName, s"onCreatePanelView( $id )")

		super.onCreatePanelView( id )
	}

	override def onDestroy()
	{
		Log.d( getClass.getName, s"onDestroy()")

		super.onDestroy()
	}

	override def onTitleChanged( title: CharSequence, color: Int )
	{
		Log.d( getClass.getName, s"onTitleChanged( $title, $color )")

		super.onTitleChanged( title, color )
	}

	override def onSupportActionModeStarted( mode: view.ActionMode )
	{
		Log.d( getClass.getName, s"onSupportActionModeStarted( $mode )")

		super.onSupportActionModeStarted( mode )
	}

	override def onSupportActionModeFinished( mode: view.ActionMode )
	{
		Log.d( getClass.getName, s"onSupportActionModeFinished( $mode )")

		super.onSupportActionModeFinished( mode )
	}

	override def onCreatePanelMenu( id: Int, menu: Menu ) =
	{
		Log.d( getClass.getName, s"onCreatePanelMenu( $id, $menu )")

		super.onCreatePanelMenu( id, menu )
	}

	override def onPreparePanel( id: Int, view: View, menu: Menu ) =
	{
		Log.d( getClass.getName, s"onPreparePanel( $id, $view, $menu )")

		super.onPreparePanel( id, view, menu )
	}

	override def onPanelClosed( id: Int, menu: Menu )
	{
		Log.d( getClass.getName, s"onPanelClosed( $id, $menu )")

		super.onPanelClosed( id, menu )
	}

	override def onMenuOpened( id: Int, menu: Menu ) =
	{
		Log.d( getClass.getName, s"onMenuOpened( $id, $menu )")

		super.onMenuOpened( id, menu )
	}

	override def onPrepareOptionsPanel( view: View, menu: Menu ) =
	{
		Log.d( getClass.getName, s"onPrepareOptionsPanel( $view, $menu )")

		super.onPrepareOptionsPanel( view, menu )
	}

	override def onBackPressed()
	{
		Log.d( getClass.getName, s"onBackPressed()")

		super.onBackPressed()
	}

	override def onCreateSupportNavigateUpTaskStack( builder: v4.app.TaskStackBuilder )
	{
		Log.d( getClass.getName, s"onCreateSupportNavigateUpTaskStack( $builder )")

		super.onCreateSupportNavigateUpTaskStack( builder )
	}

	override def onPrepareSupportNavigateUpTaskStack( builder: v4.app.TaskStackBuilder )
	{
		Log.d( getClass.getName, s"onPrepareSupportNavigateUpTaskStack( $builder )")

		super.onPrepareSupportNavigateUpTaskStack( builder )
	}

	override def onSupportNavigateUp() =
	{
		Log.d( getClass.getName, s"onSupportNavigateUp()")

		super.onSupportNavigateUp()
	}

	override def onKeyShortcut( key: Int, event: KeyEvent ) =
	{
		Log.d( getClass.getName, s"onKeyShortcut( $key, $event )")

		super.onKeyShortcut( key, event )
	}

	override def onKeyDown( key: Int, event: KeyEvent ) =
	{
		Log.d( getClass.getName, s"onKeyDown( $key, $event )")

		super.onKeyDown( key, event )
	}

	override def onSupportContentChanged()
	{
		Log.d( getClass.getName, s"onSupportContentChanged()")

		super.onSupportContentChanged()
	}

	override def onCreateView( name: String, context: Context, attributes: AttributeSet ) =
	{
		Log.d( getClass.getName, s"onCreateView( $name, $context, $attributes )")

		super.onCreateView( name, context, attributes )
	}

	override def onActivityResult( request: Int, result: Int, data: Intent )
	{
		Log.d( getClass.getName, s"onActivityResult( $request, $result, $data )")

		super.onActivityResult( request, result, data )
	}

	override def onLowMemory()
	{
		Log.d( getClass.getName, s"onLowMemory()")

		super.onLowMemory()
	}

	override def onPause()
	{
		Log.d( getClass.getName, s"onPause()")

		super.onPause()
	}

	override def onNewIntent( intent: Intent )
	{
		Log.d( getClass.getName, s"onNewIntent( $intent )")

		super.onNewIntent( intent )
	}

	override def onResume()
	{
		Log.d( getClass.getName, s"onResume()")

		super.onResume()
	}

	override def onResumeFragments()
	{
		Log.d( getClass.getName, s"onResumeFragments()")

		super.onResumeFragments()
	}

	override def onSaveInstanceState( state: Bundle )
	{
		Log.d( getClass.getName, s"onSaveInstanceState( $state )")

		super.onSaveInstanceState( state )
	}

	override def onStart()
	{
		Log.d( getClass.getName, s"onStart()")

		super.onStart()
	}

	override def onRetainCustomNonConfigurationInstance() =
	{
		Log.d( getClass.getName, s"onRetainCustomNonConfigurationInstance()")

		super.onRetainCustomNonConfigurationInstance()
	}

	override def onAttachFragment( fragment: support.Fragment )
	{
		Log.d( getClass.getName, s"onAttachFragment( $fragment )")

		super.onAttachFragment( fragment )
	}

	override def onRestoreInstanceState( state: Bundle )
	{
		Log.d( getClass.getName, s"onRestoreInstanceState( $state )")

		super.onRestoreInstanceState( state )
	}

	override def onPostCreate( state: Bundle )
	{
		Log.d( getClass.getName, s"onPostCreate( $state )")

		super.onPostCreate( state )
	}

	override def onRestart()
	{
		Log.d( getClass.getName, s"onRestart()")

		super.onRestart()
	}

	override def onUserLeaveHint()
	{
		Log.d( getClass.getName, s"onUserLeaveHint()")

		super.onUserLeaveHint()
	}

	override def onCreateThumbnail( bitmap: Bitmap, canvas: Canvas ) =
	{
		Log.d( getClass.getName, s"onCreateThumbnail( $bitmap, $canvas )")

		super.onCreateThumbnail( bitmap, canvas )
	}

	override def onCreateDescription() =
	{
		Log.d( getClass.getName, s"onCreateDescription()")

		super.onCreateDescription()
	}

	override def onProvideAssistData( data: Bundle )
	{
		Log.d( getClass.getName, s"onProvideAssistData( $data )")

		super.onProvideAssistData( data )
	}

	override def onKeyLongPress( key: Int, event: KeyEvent ) =
	{
		Log.d( getClass.getName, s"onKeyLongPress( $key, $event )")

		super.onKeyLongPress( key, event )
	}

	override def onKeyUp( key: Int, event: KeyEvent ) =
	{
		Log.d( getClass.getName, s"onKeyUp( $key, $event )")

		super.onKeyUp( key, event )
	}

	override def onKeyMultiple( key: Int, repeatCount: Int, event: KeyEvent ) =
	{
		Log.d( getClass.getName, s"onKeyMultiple( $key, $repeatCount, $event )")

		super.onKeyMultiple( key, repeatCount, event )
	}

	override def onTouchEvent( event: MotionEvent ) =
	{
		Log.d( getClass.getName, s"onTouchEvent( $event )")

		super.onTouchEvent( event )
	}

	override def onTrackballEvent( event: MotionEvent ) =
	{
		Log.d( getClass.getName, s"onTrackballEvent( $event )")

		super.onTrackballEvent( event )
	}

	override def onGenericMotionEvent( event: MotionEvent ) =
	{
		Log.d( getClass.getName, s"onGenericMotionEvent( $event )")

		super.onGenericMotionEvent( event )
	}

	override def onUserInteraction()
	{
		Log.d( getClass.getName, s"onUserInteraction()")

		super.onUserInteraction()
	}

	override def onWindowAttributesChanged( parameters: LayoutParams )
	{
		Log.d( getClass.getName, s"onWindowAttributesChanged( $parameters )")

		super.onWindowAttributesChanged( parameters )
	}

	override def onWindowFocusChanged( hasFocus: Boolean )
	{
		Log.d( getClass.getName, s"onWindowFocusChanged( $hasFocus )")

		super.onWindowFocusChanged( hasFocus )
	}

	override def onAttachedToWindow()
	{
		Log.d( getClass.getName, s"onAttachedToWindow()")

		super.onAttachedToWindow()
	}

	override def onDetachedFromWindow()
	{
		Log.d( getClass.getName, s"onDetachedFromWindow()")

		super.onDetachedFromWindow()
	}

	override def onOptionsItemSelected( item: MenuItem ) =
	{
		Log.d( getClass.getName, s"onOptionsItemSelected( $item )")

		super.onOptionsItemSelected( item )
	}

	override def onPrepareOptionsMenu( menu: Menu ) =
	{
		Log.d( getClass.getName, s"onPrepareOptionsMenu( $menu )")

		super.onPrepareOptionsMenu( menu )
	}

	override def onNavigateUp() =
	{
		Log.d( getClass.getName, s"onNavigateUp()")

		super.onNavigateUp()
	}

	override def onNavigateUpFromChild( child: app.Activity ) =
	{
		Log.d( getClass.getName, s"onNavigateUpFromChild( $child )")

		super.onNavigateUpFromChild( child )
	}

	override def onCreateNavigateUpTaskStack( builder: TaskStackBuilder )
	{
		Log.d( getClass.getName, s"onCreateNavigateUpTaskStack( $builder )")

		super.onCreateNavigateUpTaskStack( builder )
	}

	override def onPrepareNavigateUpTaskStack( builder: TaskStackBuilder )
	{
		Log.d( getClass.getName, s"onPrepareNavigateUpTaskStack( $builder )")

		super.onPrepareNavigateUpTaskStack( builder )
	}

	override def onOptionsMenuClosed( menu: Menu )
	{
		Log.d( getClass.getName, s"onOptionsMenuClosed( $menu )")

		super.onOptionsMenuClosed( menu )
	}

	override def onCreateContextMenu( menu: ContextMenu, view: View, info: ContextMenuInfo )
	{
		Log.d( getClass.getName, s"onCreateContextMenu( $menu, $view, $info )")

		super.onCreateContextMenu( menu, view, info )
	}

	override def onContextItemSelected( item: MenuItem ) =
	{
		Log.d( getClass.getName, s"onContextItemSelected( $item )")

		super.onContextItemSelected( item )
	}

	override def onContextMenuClosed( menu: Menu )
	{
		Log.d( getClass.getName, s"onContextMenuClosed( $menu )")

		super.onContextMenuClosed( menu )
	}

	override def onSearchRequested() =
	{
		Log.d( getClass.getName, s"onSearchRequested()")

		super.onSearchRequested()
	}

	override def onActivityReenter( result: Int, data: Intent )
	{
		Log.d( getClass.getName, s"onActivityReenter( $result, $data )")

		super.onActivityReenter( result, data )
	}

	override def onChildTitleChanged( child: app.Activity, title: CharSequence )
	{
		Log.d( getClass.getName, s"onChildTitleChanged( $child, $title )")

		super.onChildTitleChanged( child, title )
	}

	override def onCreateView( parent: View, name: String, context: Context, attributes: AttributeSet ) =
	{
		Log.d( getClass.getName, s"onCreateView( $parent, $name, $context, $attributes )")

		super.onCreateView( parent, name, context, attributes )
	}

	override def onVisibleBehindCanceled()
	{
		Log.d( getClass.getName, s"onVisibleBehindCanceled()")

		super.onVisibleBehindCanceled()
	}

	override def onEnterAnimationComplete()
	{
		Log.d( getClass.getName, s"onEnterAnimationComplete()")

		super.onEnterAnimationComplete()
	}

	override def onWindowStartingActionMode( callback: Callback ) =
	{
		Log.d( getClass.getName, s"onWindowStartingActionMode( $callback )")

		super.onWindowStartingActionMode( callback )
	}

	override def onActionModeStarted( mode: ActionMode )
	{
		Log.d( getClass.getName, s"onActionModeStarted( $mode )")

		super.onActionModeStarted( mode )
	}

	override def onActionModeFinished( mode: ActionMode )
	{
		Log.d( getClass.getName, s"onActionModeFinished( $mode )")

		super.onActionModeFinished( mode )
	}
}