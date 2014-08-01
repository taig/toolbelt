package com.taig.android.preference

import android.preference.DialogPreference

object Dialog
{
	trait NoTitle extends DialogPreference
	{
		setDialogTitle( null )
	}
}