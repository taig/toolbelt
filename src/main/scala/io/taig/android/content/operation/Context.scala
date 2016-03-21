package io.taig.android.content.operation

import java.io.File

import io.taig.android.content

import scala.reflect.{ ClassTag, classTag }

abstract class Context( val context: android.content.Context ) {
    private def checkAndCreateDirectory( directory: File ): Option[File] = {
        if ( !directory.exists() && !directory.mkdirs() ) {
            None
        } else {
            Some( directory )
        }
    }

    def getExternalOrInternalCacheDir: File = {
        Option( context.getCacheDir )
            .flatMap( checkAndCreateDirectory )
            .getOrElse( throw new IllegalStateException( "No cache directory available" ) )

        //        Option( context.getExternalCacheDir )
        //            .flatMap( checkAndCreateDirectory )
        //            .orElse( checkAndCreateDirectory( context.getCacheDir ) )
        //            .getOrElse( throw new IllegalStateException( "No cache directory available" ) )
    }

    /**
     * Get the app's default PackageInfo
     *
     * Useful to access manifest information, such as the app version.
     *
     * @return Default PackageInfo
     */
    def getPackageInfo = context.getPackageManager.getPackageInfo( context.getPackageName, 0 )

    def startActivity[A: ClassTag]() = context.startActivity( content.Intent[A]( context, classTag[A] ) )
}