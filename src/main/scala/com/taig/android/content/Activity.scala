package com.taig.android.content

import android.support.v7.app.ActionBarActivity
import com.taig.android.content

trait	Activity
extends	ActionBarActivity
with	Contextual
{
	override implicit def context = this
}

object Activity
{
	trait Creditor[+C <: Contract] extends content.Creditor[Seq[C]]
}