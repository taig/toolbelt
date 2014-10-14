package com.taig.android.widget

import android.content.Context
import android.os.{Bundle, Parcelable}
import android.util.{SparseArray, AttributeSet}
import android.view.View
import android.widget.{TextView, EditText, ViewSwitcher}
import com.taig.android.graphic.Color
import com.taig.android.widget.InlineEditText.Parameter

/**
 * A widget that can switch between an [[android.widget.EditText]] and a [[android.widget.TextView]]
 * 
 * By default it displays the TextView
 */
class InlineEditText( context: Context, attributes: AttributeSet ) extends ViewSwitcher( context, attributes )
{
	def this( context: Context ) = this( context, null )

	val edit = new EditText( context, attributes )

	val text = new TextView( context, attributes, android.R.attr.editTextStyle )

	edit.setId( View.NO_ID )

	text.setId( View.NO_ID )
	text.setBackgroundColor( Color.Transparent )
	text.setInputType( 0 )

	addView( text )
	addView( edit )

	def showText() = setDisplayedChild( 0 )

	def showEdit() = setDisplayedChild( 1 )

	def getText = edit.getText

	def setText( text: CharSequence )
	{
		this.edit.setText( text )
		this.text.setText( text )
	}

	def setText( resId: Int )
	{
		this.edit.setText( resId )
		this.text.setText( resId )
	}

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
		bundle.putParcelable( Parameter.Instance, InlineEditText.super.onSaveInstanceState )
		bundle.putParcelable( Parameter.Edit, edit.onSaveInstanceState() )
		bundle.putParcelable( Parameter.Text, text.onSaveInstanceState() )
		bundle.putInt( Parameter.Current, getDisplayedChild )

		bundle
	}

	override def dispatchSaveInstanceState( container: SparseArray[Parcelable] ) = super.dispatchFreezeSelfOnly( container )

	override def onRestoreInstanceState( state: Parcelable ) = state match
	{
		case bundle: Bundle =>
		{
			super.onRestoreInstanceState( bundle.getParcelable( Parameter.Instance ) )
			edit.onRestoreInstanceState( bundle.getParcelable( Parameter.Edit ) )
			text.onRestoreInstanceState( bundle.getParcelable( Parameter.Text ) )
			setDisplayedChild( bundle.getInt( Parameter.Current ) )
		}
		case _ => super.onRestoreInstanceState( state )
	}

	override def dispatchRestoreInstanceState( container: SparseArray[Parcelable] ) = super.dispatchThawSelfOnly( container )
}

object InlineEditText
{
	val Parameter = new
	{
		val Current = getClass.getName + ".Current"

		val Edit = getClass.getName + ".Current"

		val Instance = getClass.getName + ".Instance"

		val Text = getClass.getName + ".Current"
	}
}