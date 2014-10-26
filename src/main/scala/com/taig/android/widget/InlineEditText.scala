package com.taig.android.widget

import android.content.Context
import android.os.{Bundle, Parcelable}
import android.util.{AttributeSet, SparseArray}
import android.view.View
import android.widget.{TextView, ViewSwitcher}
import com.taig.android.graphic.Color

/**
 * A widget that can switch between an [[android.widget.EditText]] and a [[android.widget.TextView]]
 * 
 * By default it displays the TextView
 */
class	InlineEditText( val context: Context, attributes: AttributeSet )
extends	ViewSwitcher( context, attributes )
with	Widget
{
	val input = new EditText( context, attributes )

	val label = new TextView( context, attributes, android.R.attr.editTextStyle )

	def this( context: Context ) = this( context, null )

	input.setId( View.NO_ID )

	label.setId( View.NO_ID )
	label.setBackgroundColor( Color.Transparent )
	label.setInputType( 0 )

	addView( label )
	addView( input )

	val show = new
	{
		def label() = setDisplayedChild( 0 )

		def input() = setDisplayedChild( 1 )
	}

	def getText = input.getText

	def setText( text: CharSequence )
	{
		input.setText( text )
		label.setText( text )
	}

	def setText( resource: Int )
	{
		input.setText( resource )
		label.setText( resource )
	}

	override def setDisplayedChild( whichChild: Int )
	{
		if( whichChild == 0 )
		{
			label.setText( getText )
		}

		super.setDisplayedChild( whichChild )
	}

	override def onSaveInstanceState() =
	{
		val bundle = new Bundle()
		bundle.putParcelable( "state", super.onSaveInstanceState() )
		bundle.putParcelable( "input", input.onSaveInstanceState() )
		bundle.putParcelable( "label", label.onSaveInstanceState() )
		bundle.putInt( "active", getDisplayedChild )
		bundle
	}

	protected override def dispatchSaveInstanceState( container: SparseArray[Parcelable] )
	{
		super.dispatchFreezeSelfOnly( container )
	}

	override def onRestoreInstanceState( state: Parcelable ) = state match
	{
		case bundle: Bundle =>
		{
			super.onRestoreInstanceState( bundle.getParcelable( "state" ) )
			input.onRestoreInstanceState( bundle.getParcelable( "input" ) )
			label.onRestoreInstanceState( bundle.getParcelable( "label" ) )
			setDisplayedChild( bundle.getInt( "active" ) )
		}
		case _ => super.onRestoreInstanceState( state )
	}

	protected override def dispatchRestoreInstanceState( container: SparseArray[Parcelable] )
	{
		super.dispatchThawSelfOnly( container )
	}
}