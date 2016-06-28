package io.taig.android.content.operation

import io.taig.android.intent.Intent

import scala.reflect.{ ClassTag, classTag }

abstract class Context( val context: android.content.Context ) {
    /**
     * Get the app's default PackageInfo
     *
     * Useful to access manifest information, such as the app version.
     *
     * @return Default PackageInfo
     */
    def getPackageInfo = context.getPackageManager.getPackageInfo( context.getPackageName, 0 )

    def startActivity[A: ClassTag]() = context.startActivity( Intent[A]( context, classTag[A] ) )
}