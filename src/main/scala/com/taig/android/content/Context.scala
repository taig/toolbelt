package com.taig.android.content

import android.view.LayoutInflater
import android.{content => android}

trait Context
{
	protected[content] implicit val context: android.Context

	protected[content] implicit val richContext = this

	implicit def `Id -> String`( id: Int ) = context.getString( id )

	implicit lazy val layoutInflater: LayoutInflater = LayoutInflater.from( context )

	def getExternalOrInternalCacheDir = Option( context.getExternalCacheDir ).getOrElse( context.getCacheDir )
}