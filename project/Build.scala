import sbt._
import sbt.Keys._
import android.Keys._
import android.Plugin._
import xerial.sbt.Sonatype._
import xerial.sbt.Sonatype.SonatypeKeys._

object	Build
extends	android.AutoBuild
{
	lazy val main = Project( "toolbelt", file( "." ), settings = androidBuildAar ++ sonatypeSettings )
		.settings(
			javacOptions ++= Seq( "-source", "1.7", "-target", "1.7" ),
			libraryDependencies ++= Seq(
				compilerPlugin( "org.scalamacros" % "paradise" % "2.0.1" cross CrossVersion.full ),
				"com.caverock" % "androidsvg" % "1.2.2-beta-1",
				"io.taig.android" %% "parcelable" % "1.2.6"
			),
			name := "Toolbelt",
			organization := "io.taig.android",
			publishArtifact in ( Compile, packageDoc ) := false,
			publishArtifact in ( Compile, packageSrc ) := true,
			scalaVersion := "2.11.6",
			scalacOptions ++= Seq( "-deprecation", "-feature" ),
			// @see https://github.com/pfn/android-sdk-plugin/issues/88
			sourceGenerators in Compile <<= ( sourceGenerators in Compile ) ( generators => Seq( generators.last ) ),
			version := "0.4.0-SNAPSHOT",
			minSdkVersion in Android := "11",
			platformTarget in Android := "android-22",
			targetSdkVersion in Android := "22"
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