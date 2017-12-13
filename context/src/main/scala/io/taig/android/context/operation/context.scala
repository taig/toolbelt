package io.taig.android.context.operation

import android.content.{Context, Intent}

import scala.reflect._

final class context(context: Context) {

  /**
    * Get the app's default PackageInfo
    *
    * Useful to access manifest information, such as the app version.
    *
    * @return Default PackageInfo
    */
  def getPackageInfo =
    context.getPackageManager.getPackageInfo(context.getPackageName, 0)

  def startActivity[A: ClassTag] = context.startActivity {
    new Intent(context, classTag[A].runtimeClass)
  }
}
