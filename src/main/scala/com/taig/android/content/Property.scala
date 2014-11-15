package com.taig.android.content

class	Property[+C <: Contextual]( val content: C )
extends	Contextual
{
	override implicit def context = content.context
}