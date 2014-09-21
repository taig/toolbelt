package com.taig.android.content

import android.support.v4.app.FragmentActivity

trait Activity extends android.app.Activity with Context
{
	override protected[content] implicit val context = this
}

object Activity
{
	trait Fragment extends FragmentActivity with Activity
}