package com.taig.android.preference

import android.app.AlertDialog.Builder
import android.content.Context
import android.content.DialogInterface.BUTTON_POSITIVE
import android.content.res.TypedArray
import android.preference.DialogPreference
import android.util.AttributeSet
import android.view.{View, ViewGroup}
import android.widget.{BaseAdapter, GridView}
import com.taig.android.R
import com.taig.android.conversion._
import com.taig.android.graphic.Color
import com.taig.android.widget.ColorCircle

class	ColorPicker( attributes: AttributeSet = null, style: Int = android.R.attr.dialogPreferenceStyle, theme: Int = 0 )( implicit context: Context )
extends	DialogPreference( context, attributes, style, theme )
{
	def this( context: Context, attributes: AttributeSet, style: Int, theme: Int ) = this( attributes, style, theme )( context )

	def this( context: Context, attributes: AttributeSet, style: Int ) = this( attributes, style )( context )

	def this( context: Context, attributes: AttributeSet ) = this( attributes )( context )

	def this( context: Context ) = this()( context )

	private var widget: ColorCircle = null

	private var colors: Array[Color] = null

	private var labels: Array[String] = null

	private var selection = Color.White

	private lazy val adapter = new Adapter()

	var array = context.obtainStyledAttributes( attributes, R.styleable.ColorPickerPreference )

	colors = Option( array.getTextArray( R.styleable.ColorPickerPreference_colors ) )
		.map( _.map( _.toString ) )
		.getOrElse( context.getResources.getStringArray( R.array.color_picker_preference_color_values ) )
		.map( Color( _ ) )

	labels = Option( array.getTextArray( R.styleable.ColorPickerPreference_labels ) )
		.map( _.map( _.toString ) )
		.getOrElse( context.getResources.getStringArray( R.array.color_picker_preference_color_labels ) )

	array.recycle()

	require( colors.length == labels.length, "Color and label arrays must have equal length" )

	setWidgetLayoutResource( R.layout.color_picker_preference_widget )
	setDialogLayoutResource( R.layout.color_picker_preference_dialog )

	override def onBindView( view: View )
	{
		super.onBindView( view )

		widget = view
			.findViewById( R.id.color_picker_preference_widget )
			.asInstanceOf[ColorCircle]

		widget.setColor( selection )
		widget.scale( 0.75f )

		if( !isEnabled )
		{
			widget.setVisibility( View.INVISIBLE )
		}
	}

	override def onGetDefaultValue( array: TypedArray, index: Int ): Integer = array.getInt( index, selection )

	override def onSetInitialValue( restore: Boolean, default: Any )
	{
		setColor( if( restore ) getPersistedInt( selection ) else default.asInstanceOf[Int] )
	}

	override def onPrepareDialogBuilder( builder: Builder )
	{
		super.onPrepareDialogBuilder( builder )

		builder.setPositiveButton( null, null )
	}

	override def onCreateDialogView() =
	{
		val view = super.onCreateDialogView()

		view
			.findViewById( R.id.color_picker_dialog_grid )
			.asInstanceOf[GridView]
			.setAdapter( adapter )

		view
	}

	override def onDialogClosed( result: Boolean )
	{
		super.onDialogClosed( result )

		if( result )
		{
			setColor( selection )
		}
	}

	def setColor( color: Color )
	{
		if( callChangeListener( color ) )
		{
			selection = color
			persistInt( color )
			setSummary( labels( colors.indexOf( color ) ) )
			notifyChanged()
		}
	}

	private class Adapter extends BaseAdapter
	{
		private val circles: Array[ColorCircle] = colors.map( color => new ColorCircle()
		{
			setColor( color )

			setActive( color == selection )

			setOnClickListener( ( _: View ) =>
			{
				selection = color
				circles.filter( _.isActive ).map( _.deactivate() )
				activate()
				Option( getDialog ).foreach( dialog =>
				{
					onClick( dialog, BUTTON_POSITIVE )
					dialog.dismiss()
				} )
			} )
		} )

		override def getCount = colors.length

		override def getItemId( position: Int ) = position

		override def getView( position: Int, convertView: View, parent: ViewGroup ) = circles( position )

		override def getItem( position: Int ) = null
	}
}