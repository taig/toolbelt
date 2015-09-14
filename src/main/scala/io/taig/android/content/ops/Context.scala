package io.taig.android.content.ops

import _root_.io.taig.android.content.Contextual
import io.taig.android.content

import scala.reflect._

trait Context extends Contextual {
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