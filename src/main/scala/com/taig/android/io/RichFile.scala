package com.taig.android.io

import java.io.File

class RichFile( file: File )
{
	def deleteRecursively()
	{
		def deleteRecursively( file: File )
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

		deleteRecursively( file )
	}
}