package io.taig.android.content.operation

import io.taig.android.content

import scala.reflect.{ ClassTag, classTag }

abstract class Context( val context: android.content.Context ) {
    def getExternalOrInternalCacheDir = Option( context.getExternalCacheDir ).getOrElse( context.getCacheDir )

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