import sbt._
import sbt.Keys._

object Build extends android.AutoBuild
{
	lazy val main = Project( "android-toolbelt", file( "." ) )
		.settings(
			name := "android-toolbelt",
			organization := "com.taig.android",
			scalaVersion := "2.11.1",
			scalacOptions ++= Seq( "-feature", "-language:implicitConversions" ), 
			version := "1.0.0"
		)
}
