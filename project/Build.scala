import sbt._
import sbt.Keys._
import android.Keys._

object Build extends android.AutoBuild
{
	lazy val main = Project( "toolbelt", file( "." ) )
		.settings(
			exportJars := true,
			name := "toolbelt",
			organization := "com.taig.android",
			scalaVersion := "2.11.2",
			scalacOptions ++= Seq( "-feature", "-language:implicitConversions", "-deprecation", "-language:dynamics", "-language:reflectiveCalls" ), 
			version := "1.0.0",
			minSdkVersion in Android := "10",
			targetSdkVersion in Android := "20"
		)
}