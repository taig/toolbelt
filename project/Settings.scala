import android.Keys._
import io.taig.sbt.sonatype.SonatypeHouserulePlugin.autoImport._
import sbt.Keys._
import sbt._

object Settings {
    val android = Def.settings(
        minSdkVersion := "13",
        platformTarget := "android-25",
        targetSdkVersion := "25",
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
        resolvers += ( "Google Maven" at "https://maven.google.com" ),
        scalacOptions ++=
            "-deprecation" ::
            "-feature" ::
            Nil,
        scalaVersion := "2.11.11"
    )

    object dependency {
        val androidSupport = "26.0.0"

        val androidSupportRecycler =
            "com.android.support" % "recyclerview-v7" % androidSupport

        val androidSupportV4 =
            "com.android.support" % "support-v4" % androidSupport

        val androidSupportV13 =
            "com.android.support" % "support-v13" % androidSupport

        val cats = "0.9.0"

        val catsCore = "org.typelevel" %% "cats-core" % cats

        val catsMacros = "org.typelevel" %% "cats-macros" % cats

        val catsKernel = "org.typelevel" %% "cats-kernel" % cats

        val monix = "2.3.0"

        val monixEval = "io.monix" %% "monix-eval" % monix

        val monixReactive = "io.monix" %% "monix-reactive" % monix

        val playServices = "10.2.4"

        val playServicesBase =
            "com.google.android.gms" % "play-services-base" % playServices

        val playServicesLocation =
            "com.google.android.gms" % "play-services-location" % playServices

        val rxJava = "io.reactivex" % "rxjava" % "1.3.0"

        val rxJavaReactiveStream =
            "io.reactivex" % "rxjava-reactive-streams" % "1.2.1"

        val rxJava2 = "io.reactivex.rxjava2" % "rxjava" % "2.1.2"
    }
}