import sbt._
import sbt.Keys._
import android.Keys._
import android.Plugin._

object	Build
extends	sbt.Build
{
	lazy val main = Project( "toolbelt", file( "." ), settings = androidBuildAar )
		.settings(
			javacOptions ++= (
				"-source" :: "1.7" ::
				"-target" :: "1.7" ::
				Nil
			),
			libraryDependencies ++= (
				compilerPlugin( "org.scalamacros" % "paradise" % "2.1.0-M5" cross CrossVersion.full ) ::
				"com.android.support" % "support-v4" % "23.0.0" ::
				"com.caverock" % "androidsvg" % "1.2.2-beta-1" ::
				"com.wnafee" % "vector-compat" % "1.0.5" ::
				"io.taig.android" %% "parcelable" % "2.3.0" ::
				Nil
			),
			name := "Toolbelt",
			organization := "io.taig.android",
			publishArtifact in ( Compile, packageDoc ) := false,
			publishArtifact in ( Compile, packageSrc ) := true,
			resolvers += Resolver.sonatypeRepo( "snapshots" ),
			scalaVersion := "2.11.7",
			scalacOptions ++= (
				"-deprecation" ::
				"-feature" ::
				Nil
			),
			version := "0.4.1-SNAPSHOT"
		)
		.settings(
			minSdkVersion in Android := "11",
			platformTarget in Android := "android-23",
			targetSdkVersion in Android := "23",
			typedResources in Android := false
		)
}