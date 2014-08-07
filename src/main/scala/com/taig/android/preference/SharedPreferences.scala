package com.taig.android.preference

import android.content.Context
import android.content.SharedPreferences.OnSharedPreferenceChangeListener
import android.preference.PreferenceManager

class SharedPreferences( preferences: android.content.SharedPreferences ) extends android.content.SharedPreferences with Dynamic
{
	def selectDynamic( key: String ) = get( key )

	def updateDynamic( key: String )( value: Any ) = put( key, value )

	def applyDynamic[T]( key: String )( args: T* ): T =
	{
		require( args.length == 1, "There must be exactly 1 argument (default value)" )
		get( key, args( 0 ) )
	}

	def get[T]( key: String ): Option[T] = Option( preferences.getAll.get( key ).asInstanceOf[T] )

	def get[T]( key: Key[T] ): T = get( key.name, key.default )

	def get[T]( key: String, default: T ): T = ( default match
	{
		case boolean: Boolean => getBoolean( key, boolean )
		case float: Float => getFloat( key, float )
		case int: Int => getInt( key, int )
		case long: Long => getLong( key, long )
		case string: String => getString( key, string )
		//case set: Set[String] => getStringSet( key, set )
		case _ => throw new RuntimeException( s"Could not apply a value for $key" )
	} ).asInstanceOf[T]

	def put[T]( key: Key[T], value: T ): Unit = put( key.name, value )

	def put( key: String, value: Any )
	{
		val editor = edit()

		value match
		{
			case boolean: Boolean => editor.putBoolean( key, boolean )
			case float: Float => editor.putFloat( key, float )
			case int: Int => editor.putInt( key, int )
			case long: Long => editor.putLong( key, long )
			case string: String => editor.putString( key, string )
			//case set: Set[String] => editor.putStringSet( key, set )
			case _ => throw new RuntimeException( s"Could not update a value for $key" )
		}

		editor.commit()
	}

	override def contains( key: String ) = preferences.contains( key )

	override def getAll = preferences.getAll

	override def getBoolean( key: String, default: Boolean ) = preferences.getBoolean( key, default )

	override def getFloat( key: String, default: Float ) = preferences.getFloat( key, default )

	override def getInt( key: String, default: Int ) = preferences.getInt( key, default )

	override def getLong( key: String, default: Long ) = preferences.getLong( key, default )

	override def getString( key: String, default: String ) = preferences.getString( key, default )

	override def getStringSet( key: String, default: java.util.Set[String] ) = preferences.getStringSet( key, default )

	override def edit() = preferences.edit()

	override def registerOnSharedPreferenceChangeListener( listener: OnSharedPreferenceChangeListener )
	{
		preferences.registerOnSharedPreferenceChangeListener( listener )
	}

	override def unregisterOnSharedPreferenceChangeListener( listener: OnSharedPreferenceChangeListener )
	{
		preferences.unregisterOnSharedPreferenceChangeListener( listener )
	}
}

object SharedPreferences
{
	def apply()( implicit context: Context ): SharedPreferences =
	{
		new SharedPreferences( PreferenceManager.getDefaultSharedPreferences( context ) )
	}

	def apply( name: String, mode: Int = Context.MODE_PRIVATE )( implicit context: Context ): SharedPreferences =
	{
		new SharedPreferences( context.getSharedPreferences( name, mode ) )
	}
}