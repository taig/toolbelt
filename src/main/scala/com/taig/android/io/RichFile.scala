package com.taig.android.io

import java.io.File

class RichFile( file: File )
{
	def deleteRecursively( file: File = this.file )
	{
		if( file.isDirectory )
		{
			file.listFiles().foreach( deleteRecursively )
		}
		else
		{
			file.delete()
		}
	}
}