package io.taig.android.graphic

import io.taig.android.Parcelable
import io.taig.android.graphic.positionable.Position

/**
 * An area within a coordinate based element
 * 
 * @param position The Area's starting point
 * @param resolution The Area's size measured from the given position
 */
@Parcelable
case class Area( position: Position, resolution: Dimension )