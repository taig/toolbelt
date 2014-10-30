package com.taig.android.widget

import android.content.Context
import android.os.{Parcelable, Bundle}
import android.util.{SparseArray, AttributeSet}
import android.view.View
import android.widget.{TextView, ViewSwitcher}
import com.taig.android.widget.Inline.Parameter

/**
 * A compound widget that can switch between an input element and a [[android.widget.TextView]]
 *
 * By default the TextView is shown
 */
abstract class	Inline( val context: Context, attributes: AttributeSet )
extends			ViewSwitcher( context, attributes )
with			Widget
{
	def this( context: Context ) = this( context, null )

	protected val input: View

	// Since EditText is the most common form element, the TextView adjusts its style to match its bounds,
	// in implementation it is necessary to style the input element accordingly
	protected val text = new TextView( context, attributes, android.R.attr.editTextStyle )

	input.setId( View.NO_ID )

	text.setId( View.NO_ID )
	text.setBackground( null )
	text.setInputType( 0 )

	addView( text )
	addView( input )

	/**
	 * Get the text that is currently visible (either by the input widget or the TextView)
	 * 
	 * @return Currently visible text
	 */
	def getText: CharSequence

	def showText() = setDisplayedChild( 0 )

	def showInput() = setDisplayedChild( 1 )

	override def setDisplayedChild( whichChild: Int )
	{
		if( whichChild == 0 )
		{
			text.setText( getText )
		}

		super.setDisplayedChild( whichChild )
	}

	override def onSaveInstanceState() =
	{
		val bundle = new Bundle()
		bundle.putParcelable( Parameter.State, super.onSaveInstanceState() )
		bundle.putParcelable( Parameter.Input, input.onSaveInstanceState() )
		bundle.putParcelable( Parameter.Text, text.onSaveInstanceState() )
		bundle.putInt( Parameter.Active, getDisplayedChild )
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
			super.onRestoreInstanceState( bundle.getParcelable( Parameter.State ) )
			input.onRestoreInstanceState( bundle.getParcelable( Parameter.Input ) )
			text.onRestoreInstanceState( bundle.getParcelable( Parameter.Text ) )
			setDisplayedChild( bundle.getInt( Parameter.Active ) )
		}
		case _ => super.onRestoreInstanceState( state )
	}

	protected override def dispatchRestoreInstanceState( container: SparseArray[Parcelable] )
	{
		super.dispatchThawSelfOnly( container )
	}
}

object Inline
{
	val Parameter = new
	{
		val Active = getClass.getName + ".Active"

		val Input = getClass.getName + ".Input"

		val State = getClass.getName + ".State"

		val Text = getClass.getName + ".Text"
	}
}