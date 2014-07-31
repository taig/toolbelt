import sbt._
import sbt.Keys._
import android.Keys._

object Build extends android.AutoBuild
{
	lazy val main = Project( "android-toolbelt", file( "." ) )
		.settings(
			minSdkVersion in Android := "10",
			name := "android-toolbelt",
			organization := "com.taig.android",
			scalaVersion := "2.11.2",
			scalacOptions ++= Seq( "-feature", "-language:implicitConversions", "-deprecation" ), 
			targetSdkVersion in Android := "20",
			version := "1.0.0"
		)
}
