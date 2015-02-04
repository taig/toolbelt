package com.taig.android.preference

import android.content.Context
import android.content.SharedPreferences.OnSharedPreferenceChangeListener
import android.preference.PreferenceManager
import scala.collection.JavaConversions._

import scala.reflect.ClassTag

class	SharedPreferences( preferences: android.content.SharedPreferences )
extends	android.content.SharedPreferences
{
	def get[T: ClassTag]( key: String ): Option[T] = Option( preferences.getAll.get( key ).asInstanceOf[T] )

	def get[T: ClassTag]( key: String, default: T ): T =
	{
		val string = implicitly[ClassTag[String]]

		val set = implicitly[ClassTag[Set[String]]]

		( implicitly[ClassTag[T]] match
		{
			case ClassTag.Boolean => getBoolean( key, default.asInstanceOf[Boolean] )
			case ClassTag.Float => getFloat( key, default.asInstanceOf[Float] )
			case ClassTag.Int => getInt( key, default.asInstanceOf[Int] )
			case ClassTag.Long => getLong( key, default.asInstanceOf[Long] )
			case `set` => getStringSet( key, default.asInstanceOf[Set[String]] )
			case `string` => getString( key, default.asInstanceOf[String] )
			case _ => throw new RuntimeException( s"Could not apply a value for $key" )
		} ).asInstanceOf[T]
	}

	def put[T: ClassTag]( key: String, value: T )
	{
		val editor = edit()

		val string = implicitly[ClassTag[String]]

		val set = implicitly[ClassTag[Set[String]]]

		implicitly[ClassTag[T]] match
		{
			case ClassTag.Boolean => editor.putBoolean( key, value.asInstanceOf[Boolean] )
			case ClassTag.Float => editor.putFloat( key, value.asInstanceOf[Float] )
			case ClassTag.Int => editor.putInt( key, value.asInstanceOf[Int] )
			case ClassTag.Long => editor.putLong( key, value.asInstanceOf[Long] )
			case `set` => editor.putStringSet( key, value.asInstanceOf[Set[String]] )
			case `string` => editor.putString( key, value.asInstanceOf[String] )
			case _ => throw new RuntimeException( s"Could not apply a value for $key" )
		}

		editor.commit()
	}

	def clear() = preferences.edit().clear().commit()

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

	def apply( name: String, mode: Int = android.content.Context.MODE_PRIVATE )( implicit context: Context ): SharedPreferences =
	{
		new SharedPreferences( context.getSharedPreferences( name, mode ) )
	}
}