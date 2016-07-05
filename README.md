# Android Toolbelt

[![Circle CI](https://circleci.com/gh/Taig/Toolbelt.svg?style=shield)](https://circleci.com/gh/Taig/Toolbelt)
[![License](https://img.shields.io/badge/license-MIT-blue.svg)](https://raw.githubusercontent.com/Taig/Gandalf/master/LICENSE)

> A *Scala on Android* library providing essential helpers

## Install

````scala
libraryDependencies ++=
    "io.taig.android" %% "toolbelt-compatibility" % "0.5.0-SNAPSHOT" ::
    "io.taig.android" %% "toolbelt-functional" % "0.5.0-SNAPSHOT" ::
    "io.taig.android" %% "toolbelt-graphic" % "0.5.0-SNAPSHOT" ::
    "io.taig.android" %% "toolbelt-intent" % "0.5.0-SNAPSHOT" ::
    "io.taig.android" %% "toolbelt-log" % "0.5.0-SNAPSHOT" ::
    "io.taig.android" %% "toolbelt-system-service" % "0.5.0-SNAPSHOT" ::
    "io.taig.android" %% "toolbelt-unit" % "0.5.0-SNAPSHOT" ::
    "io.taig.android" %% "toolbelt-util" % "0.5.0-SNAPSHOT" ::
    "io.taig.android" %% "toolbelt-context" % "0.5.0-SNAPSHOT" ::
    "io.taig.android" %% "toolbelt-widget" % "0.5.0-SNAPSHOT" ::
    "io.taig.android" %% "toolbelt-concurrent" % "0.5.0-SNAPSHOT" ::
    "io.taig.android" %% "toolbelt-resource" % "0.5.0-SNAPSHOT" ::
    "io.taig.android" %% "toolbelt-app" % "0.5.0-SNAPSHOT" ::
    Nil

libraryDependencies += "io.taig.android" %% "toolbelt" % "0.5.0-SNAPSHOT"
````

````
io.taig.android:toolbelt-toolbelt_2.11:0.5.0-SNAPSHOT [S]
  +-io.taig.android:toolbelt-app_2.11:0.5.0-SNAPSHOT [S]
  | +-com.android.support:support-v13:24.0.0
  | | +-com.android.support:support-v4:24.0.0
  | |   +-com.android.support:support-annotations:24.0.0
  | |   
  | +-com.android.support:support-v4:24.0.0
  | | +-com.android.support:support-annotations:24.0.0
  | | 
  | +-io.taig.android:toolbelt-context_2.11:0.5.0-SNAPSHOT [S]
  | | +-io.taig.android:toolbelt-log_2.11:0.5.0-SNAPSHOT [S]
  | | 
  | +-io.taig.android:toolbelt-log_2.11:0.5.0-SNAPSHOT [S]
  | +-io.taig.android:toolbelt-system-service_2.11:0.5.0-SNAPSHOT [S]
  | 
  +-io.taig.android:toolbelt-compatibility:0.5.0-SNAPSHOT
  | +-com.android.support:support-v4:24.0.0
  |   +-com.android.support:support-annotations:24.0.0
  |   
  +-io.taig.android:toolbelt-concurrent_2.11:0.5.0-SNAPSHOT [S]
  | +-io.monix:monix-eval_2.11:2.0-RC8 [S]
  | | +-io.monix:monix-execution_2.11:2.0-RC8 [S]
  | | | +-org.reactivestreams:reactive-streams:1.0.0
  | | | 
  | | +-io.monix:monix-types_2.11:2.0-RC8 [S]
  | | 
  | +-io.taig.android:toolbelt-app_2.11:0.5.0-SNAPSHOT [S]
  | | +-com.android.support:support-v13:24.0.0
  | | | +-com.android.support:support-v4:24.0.0
  | | |   +-com.android.support:support-annotations:24.0.0
  | | |   
  | | +-com.android.support:support-v4:24.0.0
  | | | +-com.android.support:support-annotations:24.0.0
  | | | 
  | | +-io.taig.android:toolbelt-context_2.11:0.5.0-SNAPSHOT [S]
  | | | +-io.taig.android:toolbelt-log_2.11:0.5.0-SNAPSHOT [S]
  | | | 
  | | +-io.taig.android:toolbelt-log_2.11:0.5.0-SNAPSHOT [S]
  | | +-io.taig.android:toolbelt-system-service_2.11:0.5.0-SNAPSHOT [S]
  | | 
  | +-io.taig.android:toolbelt-util_2.11:0.5.0-SNAPSHOT [S]
  | 
  +-io.taig.android:toolbelt-context_2.11:0.5.0-SNAPSHOT [S]
  | +-io.taig.android:toolbelt-log_2.11:0.5.0-SNAPSHOT [S]
  | 
  +-io.taig.android:toolbelt-functional_2.11:0.5.0-SNAPSHOT [S]
  | +-org.typelevel:cats-core_2.11:0.6.0 [S]
  | | +-com.github.mpilquist:simulacrum_2.11:0.7.0 [S]
  | | | +-org.typelevel:macro-compat_2.11:1.1.0 [S]
  | | | 
  | | +-org.typelevel:cats-kernel_2.11:0.6.0 [S]
  | | +-org.typelevel:cats-macros_2.11:0.6.0 [S]
  | | | +-com.github.mpilquist:simulacrum_2.11:0.7.0 [S]
  | | | | +-org.typelevel:macro-compat_2.11:1.1.0 [S]
  | | | | 
  | | | +-org.typelevel:machinist_2.11:0.4.1 [S]
  | | |   +-org.scala-lang:scala-reflect:2.11.7 [S]
  | | |   
  | | +-org.typelevel:machinist_2.11:0.4.1 [S]
  | |   +-org.scala-lang:scala-reflect:2.11.7 [S]
  | |   
  | +-org.typelevel:cats-kernel_2.11:0.6.0 [S]
  | +-org.typelevel:cats-macros_2.11:0.6.0 [S]
  |   +-com.github.mpilquist:simulacrum_2.11:0.7.0 [S]
  |   | +-org.typelevel:macro-compat_2.11:1.1.0 [S]
  |   | 
  |   +-org.typelevel:machinist_2.11:0.4.1 [S]
  |     +-org.scala-lang:scala-reflect:2.11.7 [S]
  |     
  +-io.taig.android:toolbelt-graphic_2.11:0.5.0-SNAPSHOT [S]
  +-io.taig.android:toolbelt-intent_2.11:0.5.0-SNAPSHOT [S]
  +-io.taig.android:toolbelt-log_2.11:0.5.0-SNAPSHOT [S]
  +-io.taig.android:toolbelt-resource_2.11:0.5.0-SNAPSHOT [S]
  | +-com.android.support:support-v4:24.0.0
  | | +-com.android.support:support-annotations:24.0.0
  | | 
  | +-io.taig.android:toolbelt-compatibility:0.5.0-SNAPSHOT
  | | +-com.android.support:support-v4:24.0.0
  | |   +-com.android.support:support-annotations:24.0.0
  | |   
  | +-io.taig.android:toolbelt-graphic_2.11:0.5.0-SNAPSHOT [S]
  | 
  +-io.taig.android:toolbelt-system-service_2.11:0.5.0-SNAPSHOT [S]
  +-io.taig.android:toolbelt-unit_2.11:0.5.0-SNAPSHOT [S]
  +-io.taig.android:toolbelt-util_2.11:0.5.0-SNAPSHOT [S]
  +-io.taig.android:toolbelt-widget_2.11:0.5.0-SNAPSHOT [S]
    +-com.android.support:recyclerview-v7:24.0.0
    | +-com.android.support:support-annotations:24.0.0
    | +-com.android.support:support-v4:24.0.0
    |   +-com.android.support:support-annotations:24.0.0
    |   
    +-com.android.support:support-v4:24.0.0
    | +-com.android.support:support-annotations:24.0.0
    | 
    +-io.taig.android:toolbelt-graphic_2.11:0.5.0-SNAPSHOT [S]
````