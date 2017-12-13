package io.taig.android.util.operation

import java.io.File

final class file(file: File) {
  def deleteRecursively() {
    def deleteRecursively(file: File): Unit = {
      if (file.isDirectory) {
        file.listFiles().foreach(deleteRecursively)
      } else {
        file.delete()
      }
    }

    deleteRecursively(file)
  }
}
