package com.taig.android

package object preference
{
	implicit def `android.SharedPreferences -> taig.SharedPreferences`( preferences: android.content.SharedPreferences ) =
	{
		new com.taig.android.preference.SharedPreferences( preferences )
	}
}