package com.taig.android.content

import android.support.v4.app.FragmentActivity
import com.taig.android.content

trait Activity extends android.app.Activity with Context
{
	override protected[content] implicit val context = this
}

object Activity
{
	trait Fragment extends FragmentActivity with Activity

	object Fragment
	{
		/**
		 * Flag an Activity as Creditor of an Fragment
		 *
		 * If an Activity is flagged with this trait, its hosting Fragment(s) have to implement the Contract C.
		 *
		 * @tparam C The Contract that is implemented by the Fragments
		 */
		trait Creditor[C <: Contract] extends content.Creditor[Seq[C]]
		{
			this: Fragment =>
		}
	}
}