javacOptions ++=
    "-source" :: "1.7" ::
    "-target" :: "1.7" ::
    "-Xlint:deprecation" ::
    Nil

libraryDependencies ++=
    "com.android.support" % "recyclerview-v7" % "23.1.1" ::
    "com.android.support" % "support-v4" % "23.1.1" ::
    "com.android.support" % "support-v13" % "23.1.1" ::
    Nil

name := "Toolbelt"

organization := "io.taig.android"

publishArtifact in (Compile, packageDoc) := false

resolvers += Resolver.sonatypeRepo( "snapshots" )

scalaVersion := "2.11.7"

scalacOptions ++=
    "-deprecation" ::
    "-feature" ::
    Nil

version := "0.4.4-SNAPSHOT"