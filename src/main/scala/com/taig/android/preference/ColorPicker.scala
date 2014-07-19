package com.taig.android.preference

import android.content.Context
import android.content.res.TypedArray
import android.preference.{DialogPreference, Preference}
import android.util.{AttributeSet, Log}
import android.view.{View, ViewGroup}
import android.widget.{BaseAdapter, GridView}
import com.taig.android._
import com.taig.android.preference.ColorPicker.Tag
import com.taig.android.view.ColorCircle

import scala.collection.mutable

class ColorPicker( context: Context, attributes: AttributeSet, styles: Int ) extends DialogPreference( context, attributes, styles )
{
	private var preview: ColorCircle = null

	private var color = Color.White

	private lazy val adapter = new Adapter(
		context,
		R.array.color_picker_preference_color_values,
		color,
		Some( R.array.color_picker_preference_color_names )
	)

	def this( context: Context, attributes: AttributeSet ) = this( context, attributes, android.R.attr.dialogPreferenceStyle )

	setWidgetLayoutResource( R.layout.color_picker_preference_preview )
	setDialogLayoutResource( R.layout.color_picker_preference_dialog )

	override def onBindView( view: View )
	{
		super.onBindView( view )

		preview = view.findViewById( R.id.color_picker_preview ).asInstanceOf[ColorCircle]
		preview.setStrokedColor( color )
	}

	override def onGetDefaultValue( array: TypedArray, index: Int ): Integer = array.getInt( index, Color.White )

	override def onSetInitialValue( restore: Boolean, default: Any )
	{
		setColor( if( restore ) getPersistedInt( color ) else default.asInstanceOf[Int] )
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

	override def onDependencyChanged( dependency: Preference, disableDependent: Boolean )
	{
		super.onDependencyChanged( dependency, disableDependent )
	}

	override def onDialogClosed( result: Boolean )
	{
		super.onDialogClosed( result )

		if( result )
		{
			setColor( adapter.getColor() )
		}
	}

	def setColor( color: Color )
	{
		if( callChangeListener( color ) )
		{
			this.color = color
			persistInt( color )
			notifyChanged()
			adapter.getLabel().map( setSummary )
			
			if( preview != null )
			{
				preview.setStrokedColor( color )
			}
		}
	}

	private class Adapter( val context: Context, private val colors: Array[Color], private val selection: Color, private val labels: Option[Array[String]] ) extends BaseAdapter
	{
		def this( context: Context, colorsResource: Int, selection: Color, labels: Option[Int] = None ) = this(
			context,
			context.getResources.getStringArray( colorsResource ).map( Color.apply ),
			selection,
			labels.map( context.getResources.getStringArray )
		)

		require( colors contains selection, "Selection must be an element of colors" )

		require( labels.map( _.length == colors.length ).getOrElse( true ), "Colors and labels need tohave equal length" )

		private val circles = new mutable.HashMap[Int, ColorCircle]()
		{
			for( ( color, index ) <- colors.zipWithIndex )
			{
				put( index, new ColorCircle( context, color )
				{
					if( color == selection )
					{
						activate()
					}

					setOnClickListener( ( _: View ) =>
					{
						values.filter( _.isActive ).map( _.deactivate() )
						activate()
					} )
				} )
			}
		}

		override def getCount = colors.length

		override def getItemId( position: Int ) = position

		override def getView( position: Int, convertView: View, parent: ViewGroup ) = circles( position )

		override def getItem( position: Int ): Integer = colors( position ).color

		def getColor() = circles.values.find( _.isActive ).map( _.getColor ).getOrElse
		{
			Log.w( Tag, "Could not find a selected ColorCircle which is not supposed to happen" )
			Color.White
		}

		def getLabel() = labels.map( _( colors.indexOf( getColor() ) ) )
	}
}

object ColorPicker
{
	val Tag = classOf[ColorPicker].getName
}