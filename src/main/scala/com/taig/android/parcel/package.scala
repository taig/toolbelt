package com.taig.android

import android.os.Parcel

package object parcel
{
	implicit def `Parcel -> RichParcel`( parcel: Parcel ) = new RichParcel( parcel )
}