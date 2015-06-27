import sbt._
import sbt.Keys._
import android.Keys._
import android.Plugin._
import xerial.sbt.Sonatype._

object	Build
extends	android.AutoBuild
{
	lazy val main = Project( "toolbelt", file( "." ), settings = androidBuildAar ++ sonatypeSettings )
		.settings(
			javacOptions ++= (
				"-source" :: "1.7" ::
				"-target" :: "1.7" ::
				Nil
			),
			libraryDependencies ++= (
				compilerPlugin( "org.scalamacros" % "paradise" % "2.1.0-M5" cross CrossVersion.full ) ::
				"com.android.support" % "support-v4" % "22.2.0" ::
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
			// @see https://github.com/pfn/android-sdk-plugin/issues/88
			sourceGenerators in Compile <<= ( sourceGenerators in Compile ) ( generators => Seq( generators.last ) ),
			version := "0.4.0-SNAPSHOT"
		)
		.settings(
			minSdkVersion in Android := "11",
			platformTarget in Android := "android-22",
			targetSdkVersion in Android := "22",
			typedResources in Android := false
		)
		.settings(
			description := "Essential helpers for Scala on Android",
			homepage := Some( url( "https://github.com/taig/toolbelt" ) ),
			licenses := Seq( "MIT" -> url( "https://raw.githubusercontent.com/taig/toolbelt/master/LICENSE" ) ),
			organizationHomepage := Some( url( "http://taig.io" ) ),
			pomExtra :=
			{
				<issueManagement>
					<url>https://github.com/taig/toolbelt/issues</url>
					<system>GitHub Issues</system>
				</issueManagement>
				<developers>
					<developer>
						<id>Taig</id>
						<name>Niklas Klein</name>
						<email>mail@taig.io</email>
						<url>http://taig.io/</url>
					</developer>
				</developers>
			},
			pomIncludeRepository := { _ => false },
			publishArtifact in Test := false,
			publishMavenStyle := true,
			publishTo <<= version ( version =>
			{
				val url = Some( "https://oss.sonatype.org/" )

				if( version.endsWith( "SNAPSHOT" ) )
				{
					url.map( "snapshot" at _ + "content/repositories/snapshots" )
				}
				else
				{
					url.map( "release" at _ + "service/local/staging/deploy/maven2" )
				}
			} ),
			scmInfo := Some(
				ScmInfo(
					url( "https://github.com/taig/toolbelt" ),
					"scm:git:git://github.com/taig/toolbelt.git",
					Some( "scm:git:git@github.com:taig/toolbelt.git" )
				)
			),
			startYear := Some( 2014 )
		)
}