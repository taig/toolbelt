import sbt._
import sbt.Keys._
import android.Keys._
import android.Plugin._

object Build extends android.AutoBuild
{
	lazy val main = Project( "toolbelt", file( "." ) )
		.settings( androidBuildAar: _* )
		.settings(
			libraryDependencies ++= Seq(
				"com.android.support" % "appcompat-v7" % "21.0.0",
				"com.github.japgolly.android" % "svg-android" % "2.0.6",
				"com.jpardogo.materialtabstrip" % "library" % "1.0.4",
				"org.scala-lang" % "scala-reflect" % scalaVersion.value
			),
			name := "Toolbelt",
			organization := "com.taig.android",
			publishArtifact in ( Compile, packageDoc ) := false,
			scalaVersion := "2.11.4",
			scalacOptions ++= Seq(
				"-deprecation",
				"-feature",
				"-language:dynamics",
				"-language:existentials",
				"-language:implicitConversions",
				"-language:reflectiveCalls"
			),
			// @see https://github.com/pfn/android-sdk-plugin/issues/88
			sourceGenerators in Compile <<= ( sourceGenerators in Compile ) ( generators => Seq( generators.last ) ),
			version := "0.3.1-BETA",
			minSdkVersion in Android := "10",
			platformTarget in Android := "android-21",
			targetSdkVersion in Android := "21"
		)
}