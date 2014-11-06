import sbt._
import sbt.Keys._
import android.Keys._
import android.Plugin._

object Build extends android.AutoBuild
{
	lazy val main = Project( "toolbelt", file( "." ) )
		.settings( buildAar: _* )
		.settings(
			libraryDependencies ++= Seq(
				"com.android.support" % "appcompat-v7" % "21.0.0",
				"com.android.support" % "cardview-v7" % "21.0.0",
				"com.github.japgolly.android" % "svg-android" % "2.0.6",
				"org.scala-lang" % "scala-reflect" % scalaVersion.value
			),
			name := "Toolbelt",
			organization := "com.taig.android",
			scalaVersion := "2.11.4",
			scalacOptions ++= Seq(
				"-deprecation",
				"-feature",
				"-language:dynamics",
				"-language:implicitConversions",
				"-language:reflectiveCalls"
			),
			version := "0.2.3-BETA",
			libraryProject in Android := true,
			minSdkVersion in Android := "10",
			targetSdkVersion in Android := "21"
		)
}