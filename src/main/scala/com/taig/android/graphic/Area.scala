package com.taig.android.graphic

import com.taig.android.graphic.positionable.Position
import com.taig.android.parcelable.annotation.Parcelable

/**
 * An area within a coordinate based element
 * 
 * @param position The Area's starting point
 * @param resolution The Area's size measured from the given position
 */
@Parcelable
case class Area( position: Position, resolution: Resolution )