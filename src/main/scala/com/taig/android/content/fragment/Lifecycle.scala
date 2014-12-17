package com.taig.android.content.fragment

import android.app.Activity
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.util.{AttributeSet, Log}
import android.view._
import com.taig.android.content.Fragment

trait	Lifecycle
extends	Fragment
{
	override def onAttach( activity: Activity )
	{
		Log.d( getClass.getName, s"onAttach( $activity )" )

		super.onAttach( activity )
	}

	override def onCreate( state: Bundle )
	{
		Log.d( getClass.getName, s"onCreate( $state )" )

		super.onCreate( state )
	}

	override def onCreateView( inflater: LayoutInflater, container: ViewGroup, state: Bundle ) =
	{
		Log.d( getClass.getName, s"onCreateView( $inflater, $container, $state )" )

		super.onCreateView( inflater, container, state )
	}

	override def onViewCreated( view: View, state: Bundle )
	{
		Log.d( getClass.getName, s"onViewCreated( $view, $state )" )

		super.onViewCreated( view, state )
	}

	override def onActivityCreated( state: Bundle )
	{
		Log.d( getClass.getName, s"onActivityCreated( $state )" )

		super.onActivityCreated( state )
	}

	override def onStart()
	{
		Log.d( getClass.getName, s"onStart()" )

		super.onStart()
	}

	override def onResume()
	{
		Log.d( getClass.getName, s"onResume()" )

		super.onResume()
	}

	override def onSaveInstanceState( state: Bundle )
	{
		Log.d( getClass.getName, s"onSaveInstanceState( $state )" )

		super.onSaveInstanceState( state )
	}

	override def onPause()
	{
		Log.d( getClass.getName, s"onPause()" )

		super.onPause()
	}

	override def onStop()
	{
		Log.d( getClass.getName, s"onStop()" )

		super.onStop()
	}

	override def onConfigurationChanged( configuration: Configuration )
	{
		Log.d( getClass.getName, s"onConfigurationChanged( $configuration )" )

		super.onConfigurationChanged( configuration )
	}

	override def onLowMemory()
	{
		Log.d( getClass.getName, s"onLowMemory()" )

		super.onLowMemory()
	}

	override def onDestroyView()
	{
		Log.d( getClass.getName, s"onDestroyView()" )

		super.onDestroyView()
	}

	override def onDestroy()
	{
		Log.d( getClass.getName, s"onDestroy()" )

		super.onDestroy()
	}

	override def onDetach()
	{
		Log.d( getClass.getName, s"onDetach()" )

		super.onDetach()
	}

	override def onCreateOptionsMenu( menu: Menu, inflater: MenuInflater )
	{
		Log.d( getClass.getName, s"onCreateOptionsMenu( $menu, $inflater )" )

		super.onCreateOptionsMenu( menu, inflater )
	}

	override def onPrepareOptionsMenu( menu: Menu )
	{
		Log.d( getClass.getName, s"onPrepareOptionsMenu( $menu )" )

		super.onPrepareOptionsMenu( menu )
	}

	override def onDestroyOptionsMenu()
	{
		Log.d( getClass.getName, s"onDestroyOptionsMenu()" )

		super.onDestroyOptionsMenu()
	}

	override def onOptionsItemSelected( item: MenuItem ) =
	{
		Log.d( getClass.getName, s"onOptionsItemSelected( $item )" )

		super.onOptionsItemSelected( item )
	}

	override def onOptionsMenuClosed( menu: Menu )
	{
		Log.d( getClass.getName, s"onOptionsMenuClosed( $menu )" )

		super.onOptionsMenuClosed( menu )
	}

	override def onInflate( activity: Activity, attributes: AttributeSet, state: Bundle )
	{
		Log.d( getClass.getName, s"onInflate( $activity, $attributes, $state )" )

		super.onInflate( activity, attributes, state )
	}

	override def onActivityResult( request: Int, result: Int, data: Intent )
	{
		Log.d( getClass.getName, s"onActivityResult( $request, $result, $data )" )

		super.onActivityResult( request, result, data )
	}
}