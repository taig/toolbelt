import android.Keys._
import io.taig.sbt.sonatype.SonatypeHouserulePlugin.autoImport._
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
        val androidSupport = "24.2.1"

        val androidSupportRecycler = "com.android.support" % "recyclerview-v7" % androidSupport

        val androidSupportV4 = "com.android.support" % "support-v4" % androidSupport

        val androidSupportV13 = "com.android.support" % "support-v13" % androidSupport

        val cats = "0.7.0"

        val monix = "2.0.0"

        val monixEval = "io.monix" %% "monix-eval" % monix

        val monixReactive = "io.monix" %% "monix-reactive" % monix
        
        val playServices = "9.4.0"

        val playServicesBase = "com.google.android.gms" % "play-services-base" % playServices

        val playServicesLocation = "com.google.android.gms" % "play-services-location" % playServices
    }
}