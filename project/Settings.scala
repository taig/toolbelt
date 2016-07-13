import android.Keys._
import io.taig.sbt.sonatype.Plugin.autoImport._
import sbt.Keys._
import sbt._

object Settings {
    val android = Def.settings(
        minSdkVersion := "13",
        platformTarget := "android-24",
        targetSdkVersion := "24",
        typedResources := false
    )

    val common = Def.settings(
        githubProject := "toolbelt",
        javacOptions ++=
            "-source" :: "1.7" ::
            "-target" :: "1.7" ::
            Nil,
        name := name.value.capitalize,
        normalizedName := s"toolbelt-${normalizedName.value}",
        organization := "io.taig.android",
        scalacOptions ++=
            "-deprecation" ::
            "-feature" ::
            Nil,
        scalaVersion := "2.11.8"
    )

    val noPublish = Seq(
        publish := (),
        publishLocal := (),
        publishArtifact := false
    )
    
    object dependency {
        val androidSupport = "24.0.0"
        
        val androidSupportRecycler = "com.android.support" % "recyclerview-v7" % androidSupport
        
        val androidSupportV4 = "com.android.support" % "support-v4" % androidSupport
        
        val androidSupportV13 = "com.android.support" % "support-v13" % androidSupport
        
        val cats = "0.6.0"
    }
}