package com.taig.android.preference

import android.app.AlertDialog.Builder
import android.content.{Context, DialogInterface}
import android.graphics.Typeface
import android.util.AttributeSet
import android.view.{LayoutInflater, View, ViewGroup}
import android.widget.{BaseAdapter, CheckedTextView, TextView}
import com.taig.android._

class FontPicker( context: Context, attributes: AttributeSet ) extends SummarizedList( context, attributes )
{
	private var preview = "Lorem Ipsum"

	private var widget: TextView = null

	private val fonts = getEntryValues.map( path => Typeface.createFromAsset( context.getAssets, path.toString ) )

	private val adapter = new Adapter()

	private var selection = 0

	def this( context: Context ) = this( context, null )

	var array = context.obtainStyledAttributes( attributes, R.styleable.FontPickerPreference )
	preview = Option( array.getString( R.styleable.FontPickerPreference_previewText ) ).getOrElse( preview )
	array.recycle()

	setWidgetLayoutResource( R.layout.font_picker_preference_widget )

	def getPreview = preview

	def setPreview( text: String )
	{
		this.preview = text
		widget.setText( text )
	}

	override def onSetInitialValue( restoreValue: Boolean, defaultValue: scala.Any )
	{
		super.onSetInitialValue( restoreValue, defaultValue )
		selection = findIndexOfValue( getValue )
	}

	override def onBindView( view: View ) 
	{
		super.onBindView( view )

		widget = view
			.findViewById( R.id.font_picker_preference_widget )
			.asInstanceOf[TextView]

		widget.setText( preview )
		widget.setTypeface( fonts( selection ) )

		if( !isEnabled )
		{
			widget.setVisibility( View.INVISIBLE )
		}
	}

	override def onPrepareDialogBuilder( builder: Builder )
	{
		super.onPrepareDialogBuilder( builder )

		builder.setSingleChoiceItems(
			adapter,
			findIndexOfValue( getValue ),
			( dialog: DialogInterface, which: Int ) =>
			{
				selection = which
				onClick( dialog, DialogInterface.BUTTON_POSITIVE )
				dialog.dismiss()
			}
		)
	}

	override def onDialogClosed( result: Boolean )
	{
		super.onDialogClosed( result )

		if( result )
		{
			val value = getEntryValues()( selection ).toString

			if( callChangeListener( value ) )
			{
				setValue( value )
			}
		}
	}

	private class Adapter extends BaseAdapter
	{
		private val items = fonts.zipWithIndex.map(
		{
			case ( font, index ) =>
			{
				val view = LayoutInflater
					.from( context )
					.inflate( android.R.layout.select_dialog_singlechoice, null )
					.asInstanceOf[CheckedTextView]

				view.setTextSize( 60 / context.getResources.getDisplayMetrics.scaledDensity )
				view.setTypeface( font )
				view.setChecked( findIndexOfValue( getValue ) == index )

				( index, view )
			}
		} ).toMap

		override def getCount = fonts.length

		override def getItemId( position: Int ) = position

		override def getView( position: Int, convertView: View, parent: ViewGroup ) =
		{
			val item = items( position )
			item.setText( preview )
			item
		}

		override def getItem( position: Int ) = fonts( position )
	}
}