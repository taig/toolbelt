import android.Keys._
import android.Plugin._
import sbt.Keys._
import sbt.Resolver.ivyStylePatterns
import sbt._

object	Build
extends	android.AutoBuild
{
	lazy val main = Project( "toolbelt", file( "." ) )
		.settings( androidBuildAar: _* )
		.settings(
			javacOptions ++= Seq( "-source", "1.7", "-target", "1.7" ),
			libraryDependencies ++= Seq(
				compilerPlugin( "org.scalamacros" % "paradise" % "2.0.1" cross CrossVersion.full ),
				"com.android.support" % "appcompat-v7" % "22.0.0",
				"com.android.support" % "cardview-v7" % "22.0.0",
				"com.android.support" % "recyclerview-v7" % "22.0.0",
				"com.caverock" % "androidsvg" % "1.2.2-beta-1",
				"com.jpardogo.materialtabstrip" % "library" % "1.0.9",
				"io.taig.android" %% "parcelable" % "1.2.5"
			),
			name := "Toolbelt",
			organization := "com.taig.android",
			publishArtifact in ( Compile, packageDoc ) := false,
			publishArtifact in ( Compile, packageSrc ) := true,
			scalaVersion := "2.11.6",
			scalacOptions ++= Seq(
				"-deprecation",
				"-feature",
				"-language:existentials",
				"-language:implicitConversions",
				"-language:reflectiveCalls"
			),
			// @see https://github.com/pfn/android-sdk-plugin/issues/88
			sourceGenerators in Compile <<= ( sourceGenerators in Compile ) ( generators => Seq( generators.last ) ),
			version := "0.3.40-BETA",
			minSdkVersion in Android := "10",
			platformTarget in Android := "android-22",
			targetSdkVersion in Android := "21"
		)
}