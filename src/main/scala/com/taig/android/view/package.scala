package com.taig.android

import android.view.View

package object view
{
	implicit def `Inflater -> View`[V <: View]( inflater: Inflater[V] ): V = inflater.view
}