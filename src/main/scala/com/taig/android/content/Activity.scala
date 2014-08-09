package com.taig.android.content

import android.support.v4.app.FragmentActivity

trait Activity extends android.app.Activity with Contextual
{
	override protected implicit val context = this
}

object Activity
{
	trait Fragment extends FragmentActivity with Activity
}