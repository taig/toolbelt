package com.taig.android

import java.io.File

package object io
{
	implicit def `File -> RichFile`( file: File ) = new RichFile( file )
}
