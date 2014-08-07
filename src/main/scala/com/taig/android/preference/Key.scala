package com.taig.android.preference

//import scala.Predef.{ String => S }
//import scala.{ Boolean => B }
//import scala.{ Int => I }
//import scala.{ Float => F }
//import scala.{ Long => L }

case class Key[T]( name: String, default: T )
{
	override def toString = name
}

object Key
{
//	def apply[T]( name: S, default: T ) = new Key( name, default )
//
//	def unapply[T]( key: Key[T] ) = ( key.name, key.default )

//	case class Boolean( override val name: S, override val default: B = false ) extends Key( name, default )
//
//	case class Float( override val name: S, override val default: F = 0 ) extends Key( name, default )
//
//	case class Int( override val name: S, override val default: I = 0 ) extends Key( name, default )
//
//	case class Long( override val name: S, override val default: L = 0 ) extends Key( name, default )
//
//	case class String( override val name: S, override val default: S = "" ) extends Key( name, default )
}