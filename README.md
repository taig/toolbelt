# Android Toolbelt

[![Circle CI](https://circleci.com/gh/Taig/toolbelt.svg?style=shield)](https://circleci.com/gh/Taig/toolbelt)
[![License](https://img.shields.io/badge/license-MIT-blue.svg)](https://raw.githubusercontent.com/Taig/toolbelt/master/LICENSE)

> A *Scala on Android* library providing essential helpers

## Install

```scala
libraryDependencies ++=
    "io.taig.android" %% "toolbelt-compatibility" % "0.8.0" ::
    "io.taig.android" %% "toolbelt-functional" % "0.8.0" ::
    "io.taig.android" %% "toolbelt-graphic" % "0.8.0" ::
    "io.taig.android" %% "toolbelt-intent" % "0.8.0" ::
    "io.taig.android" %% "toolbelt-log" % "0.8.0" ::
    "io.taig.android" %% "toolbelt-mosby" % "0.8.0" ::
    "io.taig.android" %% "toolbelt-system-service" % "0.8.0" ::
    "io.taig.android" %% "toolbelt-unit" % "0.8.0" ::
    "io.taig.android" %% "toolbelt-util" % "0.8.0" ::
    "io.taig.android" %% "toolbelt-context" % "0.8.0" ::
    "io.taig.android" %% "toolbelt-widget" % "0.8.0" ::
    "io.taig.android" %% "toolbelt-concurrent" % "0.8.0" ::
    "io.taig.android" %% "toolbelt-resource" % "0.8.0" ::
    "io.taig.android" %% "toolbelt-app" % "0.8.0" ::
    Nil

libraryDependencies += "io.taig.android" %% "toolbelt" % "0.8.0"
```

```
io.taig.android:toolbelt_2.11:0.8.0 [S]
  +-io.taig.android:toolbelt-app_2.11:0.8.0 [S]
  | +-com.android.support:support-v13:25.2.0
  | | +-com.android.support:support-annotations:25.2.0
  | | +-com.android.support:support-v4:25.2.0
  | |   +-com.android.support:support-compat:25.2.0
  | |   | +-com.android.support:support-annotations:25.2.0
  | |   |
  | |   +-com.android.support:support-core-ui:25.2.0
  | |   | +-com.android.support:support-annotations:25.2.0
  | |   | +-com.android.support:support-compat:25.2.0
  | |   |   +-com.android.support:support-annotations:25.2.0
  | |   |   
  | |   +-com.android.support:support-core-utils:25.2.0
  | |   | +-com.android.support:support-annotations:25.2.0
  | |   | +-com.android.support:support-compat:25.2.0
  | |   |   +-com.android.support:support-annotations:25.2.0
  | |   |   
  | |   +-com.android.support:support-fragment:25.2.0
  | |   | +-com.android.support:support-compat:25.2.0
  | |   | | +-com.android.support:support-annotations:25.2.0
  | |   | |
  | |   | +-com.android.support:support-core-ui:25.2.0
  | |   | | +-com.android.support:support-annotations:25.2.0
  | |   | | +-com.android.support:support-compat:25.2.0
  | |   | |   +-com.android.support:support-annotations:25.2.0
  | |   | |   
  | |   | +-com.android.support:support-core-utils:25.2.0
  | |   | | +-com.android.support:support-annotations:25.2.0
  | |   | | +-com.android.support:support-compat:25.2.0
  | |   | |   +-com.android.support:support-annotations:25.2.0
  | |   | |   
  | |   | +-com.android.support:support-media-compat:25.2.0
  | |   |   +-com.android.support:support-annotations:25.2.0
  | |   |   +-com.android.support:support-compat:25.2.0
  | |   |     +-com.android.support:support-annotations:25.2.0
  | |   |     
  | |   +-com.android.support:support-media-compat:25.2.0
  | |     +-com.android.support:support-annotations:25.2.0
  | |     +-com.android.support:support-compat:25.2.0
  | |       +-com.android.support:support-annotations:25.2.0
  | |       
  | +-com.android.support:support-v4:25.2.0
  | | +-com.android.support:support-compat:25.2.0
  | | | +-com.android.support:support-annotations:25.2.0
  | | |
  | | +-com.android.support:support-core-ui:25.2.0
  | | | +-com.android.support:support-annotations:25.2.0
  | | | +-com.android.support:support-compat:25.2.0
  | | |   +-com.android.support:support-annotations:25.2.0
  | | |   
  | | +-com.android.support:support-core-utils:25.2.0
  | | | +-com.android.support:support-annotations:25.2.0
  | | | +-com.android.support:support-compat:25.2.0
  | | |   +-com.android.support:support-annotations:25.2.0
  | | |   
  | | +-com.android.support:support-fragment:25.2.0
  | | | +-com.android.support:support-compat:25.2.0
  | | | | +-com.android.support:support-annotations:25.2.0
  | | | |
  | | | +-com.android.support:support-core-ui:25.2.0
  | | | | +-com.android.support:support-annotations:25.2.0
  | | | | +-com.android.support:support-compat:25.2.0
  | | | |   +-com.android.support:support-annotations:25.2.0
  | | | |   
  | | | +-com.android.support:support-core-utils:25.2.0
  | | | | +-com.android.support:support-annotations:25.2.0
  | | | | +-com.android.support:support-compat:25.2.0
  | | | |   +-com.android.support:support-annotations:25.2.0
  | | | |   
  | | | +-com.android.support:support-media-compat:25.2.0
  | | |   +-com.android.support:support-annotations:25.2.0
  | | |   +-com.android.support:support-compat:25.2.0
  | | |     +-com.android.support:support-annotations:25.2.0
  | | |     
  | | +-com.android.support:support-media-compat:25.2.0
  | |   +-com.android.support:support-annotations:25.2.0
  | |   +-com.android.support:support-compat:25.2.0
  | |     +-com.android.support:support-annotations:25.2.0
  | |     
  | +-io.taig.android:toolbelt-context_2.11:0.8.0 [S]
  | | +-io.taig.android:toolbelt-log_2.11:0.8.0 [S]
  | |
  | +-io.taig.android:toolbelt-log_2.11:0.8.0 [S]
  | +-io.taig.android:toolbelt-system-service_2.11:0.8.0 [S]
  |
  +-io.taig.android:toolbelt-compatibility:0.8.0
  | +-com.android.support:support-v4:25.2.0
  |   +-com.android.support:support-compat:25.2.0
  |   | +-com.android.support:support-annotations:25.2.0
  |   |
  |   +-com.android.support:support-core-ui:25.2.0
  |   | +-com.android.support:support-annotations:25.2.0
  |   | +-com.android.support:support-compat:25.2.0
  |   |   +-com.android.support:support-annotations:25.2.0
  |   |   
  |   +-com.android.support:support-core-utils:25.2.0
  |   | +-com.android.support:support-annotations:25.2.0
  |   | +-com.android.support:support-compat:25.2.0
  |   |   +-com.android.support:support-annotations:25.2.0
  |   |   
  |   +-com.android.support:support-fragment:25.2.0
  |   | +-com.android.support:support-compat:25.2.0
  |   | | +-com.android.support:support-annotations:25.2.0
  |   | |
  |   | +-com.android.support:support-core-ui:25.2.0
  |   | | +-com.android.support:support-annotations:25.2.0
  |   | | +-com.android.support:support-compat:25.2.0
  |   | |   +-com.android.support:support-annotations:25.2.0
  |   | |   
  |   | +-com.android.support:support-core-utils:25.2.0
  |   | | +-com.android.support:support-annotations:25.2.0
  |   | | +-com.android.support:support-compat:25.2.0
  |   | |   +-com.android.support:support-annotations:25.2.0
  |   | |   
  |   | +-com.android.support:support-media-compat:25.2.0
  |   |   +-com.android.support:support-annotations:25.2.0
  |   |   +-com.android.support:support-compat:25.2.0
  |   |     +-com.android.support:support-annotations:25.2.0
  |   |     
  |   +-com.android.support:support-media-compat:25.2.0
  |     +-com.android.support:support-annotations:25.2.0
  |     +-com.android.support:support-compat:25.2.0
  |       +-com.android.support:support-annotations:25.2.0
  |       
  +-io.taig.android:toolbelt-concurrent_2.11:0.8.0 [S]
  | +-io.monix:monix-eval_2.11:2.2.2 [S]
  | | +-io.monix:monix-execution_2.11:2.2.2 [S]
  | | | +-org.reactivestreams:reactive-streams:1.0.0
  | | |
  | | +-io.monix:monix-types_2.11:2.2.2 [S]
  | |
  | +-io.taig.android:toolbelt-app_2.11:0.8.0 [S]
  | | +-com.android.support:support-v13:25.2.0
  | | | +-com.android.support:support-annotations:25.2.0
  | | | +-com.android.support:support-v4:25.2.0
  | | |   +-com.android.support:support-compat:25.2.0
  | | |   | +-com.android.support:support-annotations:25.2.0
  | | |   |
  | | |   +-com.android.support:support-core-ui:25.2.0
  | | |   | +-com.android.support:support-annotations:25.2.0
  | | |   | +-com.android.support:support-compat:25.2.0
  | | |   |   +-com.android.support:support-annotations:25.2.0
  | | |   |   
  | | |   +-com.android.support:support-core-utils:25.2.0
  | | |   | +-com.android.support:support-annotations:25.2.0
  | | |   | +-com.android.support:support-compat:25.2.0
  | | |   |   +-com.android.support:support-annotations:25.2.0
  | | |   |   
  | | |   +-com.android.support:support-fragment:25.2.0
  | | |   | +-com.android.support:support-compat:25.2.0
  | | |   | | +-com.android.support:support-annotations:25.2.0
  | | |   | |
  | | |   | +-com.android.support:support-core-ui:25.2.0
  | | |   | | +-com.android.support:support-annotations:25.2.0
  | | |   | | +-com.android.support:support-compat:25.2.0
  | | |   | |   +-com.android.support:support-annotations:25.2.0
  | | |   | |   
  | | |   | +-com.android.support:support-core-utils:25.2.0
  | | |   | | +-com.android.support:support-annotations:25.2.0
  | | |   | | +-com.android.support:support-compat:25.2.0
  | | |   | |   +-com.android.support:support-annotations:25.2.0
  | | |   | |   
  | | |   | +-com.android.support:support-media-compat:25.2.0
  | | |   |   +-com.android.support:support-annotations:25.2.0
  | | |   |   +-com.android.support:support-compat:25.2.0
  | | |   |     +-com.android.support:support-annotations:25.2.0
  | | |   |     
  | | |   +-com.android.support:support-media-compat:25.2.0
  | | |     +-com.android.support:support-annotations:25.2.0
  | | |     +-com.android.support:support-compat:25.2.0
  | | |       +-com.android.support:support-annotations:25.2.0
  | | |       
  | | +-com.android.support:support-v4:25.2.0
  | | | +-com.android.support:support-compat:25.2.0
  | | | | +-com.android.support:support-annotations:25.2.0
  | | | |
  | | | +-com.android.support:support-core-ui:25.2.0
  | | | | +-com.android.support:support-annotations:25.2.0
  | | | | +-com.android.support:support-compat:25.2.0
  | | | |   +-com.android.support:support-annotations:25.2.0
  | | | |   
  | | | +-com.android.support:support-core-utils:25.2.0
  | | | | +-com.android.support:support-annotations:25.2.0
  | | | | +-com.android.support:support-compat:25.2.0
  | | | |   +-com.android.support:support-annotations:25.2.0
  | | | |   
  | | | +-com.android.support:support-fragment:25.2.0
  | | | | +-com.android.support:support-compat:25.2.0
  | | | | | +-com.android.support:support-annotations:25.2.0
  | | | | |
  | | | | +-com.android.support:support-core-ui:25.2.0
  | | | | | +-com.android.support:support-annotations:25.2.0
  | | | | | +-com.android.support:support-compat:25.2.0
  | | | | |   +-com.android.support:support-annotations:25.2.0
  | | | | |   
  | | | | +-com.android.support:support-core-utils:25.2.0
  | | | | | +-com.android.support:support-annotations:25.2.0
  | | | | | +-com.android.support:support-compat:25.2.0
  | | | | |   +-com.android.support:support-annotations:25.2.0
  | | | | |   
  | | | | +-com.android.support:support-media-compat:25.2.0
  | | | |   +-com.android.support:support-annotations:25.2.0
  | | | |   +-com.android.support:support-compat:25.2.0
  | | | |     +-com.android.support:support-annotations:25.2.0
  | | | |     
  | | | +-com.android.support:support-media-compat:25.2.0
  | | |   +-com.android.support:support-annotations:25.2.0
  | | |   +-com.android.support:support-compat:25.2.0
  | | |     +-com.android.support:support-annotations:25.2.0
  | | |     
  | | +-io.taig.android:toolbelt-context_2.11:0.8.0 [S]
  | | | +-io.taig.android:toolbelt-log_2.11:0.8.0 [S]
  | | |
  | | +-io.taig.android:toolbelt-log_2.11:0.8.0 [S]
  | | +-io.taig.android:toolbelt-system-service_2.11:0.8.0 [S]
  | |
  | +-io.taig.android:toolbelt-util_2.11:0.8.0 [S]
  |
  +-io.taig.android:toolbelt-context_2.11:0.8.0 [S]
  | +-io.taig.android:toolbelt-log_2.11:0.8.0 [S]
  |
  +-io.taig.android:toolbelt-functional_2.11:0.8.0 [S]
  | +-org.typelevel:cats-core_2.11:0.9.0 [S]
  | | +-com.github.mpilquist:simulacrum_2.11:0.10.0 [S]
  | | | +-org.typelevel:macro-compat_2.11:1.1.1 [S]
  | | |
  | | +-org.typelevel:cats-kernel_2.11:0.9.0 [S]
  | | +-org.typelevel:cats-macros_2.11:0.9.0 [S]
  | | | +-com.github.mpilquist:simulacrum_2.11:0.10.0 [S]
  | | | | +-org.typelevel:macro-compat_2.11:1.1.1 [S]
  | | | |
  | | | +-org.typelevel:machinist_2.11:0.8.0 [S]
  | | |   +-org.scala-lang:scala-reflect:2.11.8 [S]
  | | |   
  | | +-org.typelevel:machinist_2.11:0.8.0 [S]
  | |   +-org.scala-lang:scala-reflect:2.11.8 [S]
  | |   
  | +-org.typelevel:cats-kernel_2.11:0.9.0 [S]
  | +-org.typelevel:cats-macros_2.11:0.9.0 [S]
  |   +-com.github.mpilquist:simulacrum_2.11:0.10.0 [S]
  |   | +-org.typelevel:macro-compat_2.11:1.1.1 [S]
  |   |
  |   +-org.typelevel:machinist_2.11:0.8.0 [S]
  |     +-org.scala-lang:scala-reflect:2.11.8 [S]
  |     
  +-io.taig.android:toolbelt-graphic_2.11:0.8.0 [S]
  +-io.taig.android:toolbelt-intent_2.11:0.8.0 [S]
  +-io.taig.android:toolbelt-log_2.11:0.8.0 [S]
  +-io.taig.android:toolbelt-monix_2.11:0.8.0 [S]
  | +-com.google.android.gms:play-services-base:10.2.0
  | | +-com.google.android.gms:play-services-basement:10.2.0
  | | | +-com.android.support:support-v4:24.0.0 (evicted by: 25.2.0)
  | | | +-com.android.support:support-v4:25.2.0
  | | |   +-com.android.support:support-compat:25.2.0
  | | |   | +-com.android.support:support-annotations:25.2.0
  | | |   |
  | | |   +-com.android.support:support-core-ui:25.2.0
  | | |   | +-com.android.support:support-annotations:25.2.0
  | | |   | +-com.android.support:support-compat:25.2.0
  | | |   |   +-com.android.support:support-annotations:25.2.0
  | | |   |   
  | | |   +-com.android.support:support-core-utils:25.2.0
  | | |   | +-com.android.support:support-annotations:25.2.0
  | | |   | +-com.android.support:support-compat:25.2.0
  | | |   |   +-com.android.support:support-annotations:25.2.0
  | | |   |   
  | | |   +-com.android.support:support-fragment:25.2.0
  | | |   | +-com.android.support:support-compat:25.2.0
  | | |   | | +-com.android.support:support-annotations:25.2.0
  | | |   | |
  | | |   | +-com.android.support:support-core-ui:25.2.0
  | | |   | | +-com.android.support:support-annotations:25.2.0
  | | |   | | +-com.android.support:support-compat:25.2.0
  | | |   | |   +-com.android.support:support-annotations:25.2.0
  | | |   | |   
  | | |   | +-com.android.support:support-core-utils:25.2.0
  | | |   | | +-com.android.support:support-annotations:25.2.0
  | | |   | | +-com.android.support:support-compat:25.2.0
  | | |   | |   +-com.android.support:support-annotations:25.2.0
  | | |   | |   
  | | |   | +-com.android.support:support-media-compat:25.2.0
  | | |   |   +-com.android.support:support-annotations:25.2.0
  | | |   |   +-com.android.support:support-compat:25.2.0
  | | |   |     +-com.android.support:support-annotations:25.2.0
  | | |   |     
  | | |   +-com.android.support:support-media-compat:25.2.0
  | | |     +-com.android.support:support-annotations:25.2.0
  | | |     +-com.android.support:support-compat:25.2.0
  | | |       +-com.android.support:support-annotations:25.2.0
  | | |       
  | | +-com.google.android.gms:play-services-tasks:10.2.0
  | |   +-com.google.android.gms:play-services-basement:10.2.0
  | |     +-com.android.support:support-v4:24.0.0 (evicted by: 25.2.0)
  | |     +-com.android.support:support-v4:25.2.0
  | |       +-com.android.support:support-compat:25.2.0
  | |       | +-com.android.support:support-annotations:25.2.0
  | |       |
  | |       +-com.android.support:support-core-ui:25.2.0
  | |       | +-com.android.support:support-annotations:25.2.0
  | |       | +-com.android.support:support-compat:25.2.0
  | |       |   +-com.android.support:support-annotations:25.2.0
  | |       |   
  | |       +-com.android.support:support-core-utils:25.2.0
  | |       | +-com.android.support:support-annotations:25.2.0
  | |       | +-com.android.support:support-compat:25.2.0
  | |       |   +-com.android.support:support-annotations:25.2.0
  | |       |   
  | |       +-com.android.support:support-fragment:25.2.0
  | |       | +-com.android.support:support-compat:25.2.0
  | |       | | +-com.android.support:support-annotations:25.2.0
  | |       | |
  | |       | +-com.android.support:support-core-ui:25.2.0
  | |       | | +-com.android.support:support-annotations:25.2.0
  | |       | | +-com.android.support:support-compat:25.2.0
  | |       | |   +-com.android.support:support-annotations:25.2.0
  | |       | |   
  | |       | +-com.android.support:support-core-utils:25.2.0
  | |       | | +-com.android.support:support-annotations:25.2.0
  | |       | | +-com.android.support:support-compat:25.2.0
  | |       | |   +-com.android.support:support-annotations:25.2.0
  | |       | |   
  | |       | +-com.android.support:support-media-compat:25.2.0
  | |       |   +-com.android.support:support-annotations:25.2.0
  | |       |   +-com.android.support:support-compat:25.2.0
  | |       |     +-com.android.support:support-annotations:25.2.0
  | |       |     
  | |       +-com.android.support:support-media-compat:25.2.0
  | |         +-com.android.support:support-annotations:25.2.0
  | |         +-com.android.support:support-compat:25.2.0
  | |           +-com.android.support:support-annotations:25.2.0
  | |           
  | +-com.google.android.gms:play-services-location:10.2.0
  | | +-com.google.android.gms:play-services-base:10.2.0
  | | | +-com.google.android.gms:play-services-basement:10.2.0
  | | | | +-com.android.support:support-v4:24.0.0 (evicted by: 25.2.0)
  | | | | +-com.android.support:support-v4:25.2.0
  | | | |   +-com.android.support:support-compat:25.2.0
  | | | |   | +-com.android.support:support-annotations:25.2.0
  | | | |   |
  | | | |   +-com.android.support:support-core-ui:25.2.0
  | | | |   | +-com.android.support:support-annotations:25.2.0
  | | | |   | +-com.android.support:support-compat:25.2.0
  | | | |   |   +-com.android.support:support-annotations:25.2.0
  | | | |   |   
  | | | |   +-com.android.support:support-core-utils:25.2.0
  | | | |   | +-com.android.support:support-annotations:25.2.0
  | | | |   | +-com.android.support:support-compat:25.2.0
  | | | |   |   +-com.android.support:support-annotations:25.2.0
  | | | |   |   
  | | | |   +-com.android.support:support-fragment:25.2.0
  | | | |   | +-com.android.support:support-compat:25.2.0
  | | | |   | | +-com.android.support:support-annotations:25.2.0
  | | | |   | |
  | | | |   | +-com.android.support:support-core-ui:25.2.0
  | | | |   | | +-com.android.support:support-annotations:25.2.0
  | | | |   | | +-com.android.support:support-compat:25.2.0
  | | | |   | |   +-com.android.support:support-annotations:25.2.0
  | | | |   | |   
  | | | |   | +-com.android.support:support-core-utils:25.2.0
  | | | |   | | +-com.android.support:support-annotations:25.2.0
  | | | |   | | +-com.android.support:support-compat:25.2.0
  | | | |   | |   +-com.android.support:support-annotations:25.2.0
  | | | |   | |   
  | | | |   | +-com.android.support:support-media-compat:25.2.0
  | | | |   |   +-com.android.support:support-annotations:25.2.0
  | | | |   |   +-com.android.support:support-compat:25.2.0
  | | | |   |     +-com.android.support:support-annotations:25.2.0
  | | | |   |     
  | | | |   +-com.android.support:support-media-compat:25.2.0
  | | | |     +-com.android.support:support-annotations:25.2.0
  | | | |     +-com.android.support:support-compat:25.2.0
  | | | |       +-com.android.support:support-annotations:25.2.0
  | | | |       
  | | | +-com.google.android.gms:play-services-tasks:10.2.0
  | | |   +-com.google.android.gms:play-services-basement:10.2.0
  | | |     +-com.android.support:support-v4:24.0.0 (evicted by: 25.2.0)
  | | |     +-com.android.support:support-v4:25.2.0
  | | |       +-com.android.support:support-compat:25.2.0
  | | |       | +-com.android.support:support-annotations:25.2.0
  | | |       |
  | | |       +-com.android.support:support-core-ui:25.2.0
  | | |       | +-com.android.support:support-annotations:25.2.0
  | | |       | +-com.android.support:support-compat:25.2.0
  | | |       |   +-com.android.support:support-annotations:25.2.0
  | | |       |   
  | | |       +-com.android.support:support-core-utils:25.2.0
  | | |       | +-com.android.support:support-annotations:25.2.0
  | | |       | +-com.android.support:support-compat:25.2.0
  | | |       |   +-com.android.support:support-annotations:25.2.0
  | | |       |   
  | | |       +-com.android.support:support-fragment:25.2.0
  | | |       | +-com.android.support:support-compat:25.2.0
  | | |       | | +-com.android.support:support-annotations:25.2.0
  | | |       | |
  | | |       | +-com.android.support:support-core-ui:25.2.0
  | | |       | | +-com.android.support:support-annotations:25.2.0
  | | |       | | +-com.android.support:support-compat:25.2.0
  | | |       | |   +-com.android.support:support-annotations:25.2.0
  | | |       | |   
  | | |       | +-com.android.support:support-core-utils:25.2.0
  | | |       | | +-com.android.support:support-annotations:25.2.0
  | | |       | | +-com.android.support:support-compat:25.2.0
  | | |       | |   +-com.android.support:support-annotations:25.2.0
  | | |       | |   
  | | |       | +-com.android.support:support-media-compat:25.2.0
  | | |       |   +-com.android.support:support-annotations:25.2.0
  | | |       |   +-com.android.support:support-compat:25.2.0
  | | |       |     +-com.android.support:support-annotations:25.2.0
  | | |       |     
  | | |       +-com.android.support:support-media-compat:25.2.0
  | | |         +-com.android.support:support-annotations:25.2.0
  | | |         +-com.android.support:support-compat:25.2.0
  | | |           +-com.android.support:support-annotations:25.2.0
  | | |           
  | | +-com.google.android.gms:play-services-basement:10.2.0
  | | | +-com.android.support:support-v4:24.0.0 (evicted by: 25.2.0)
  | | | +-com.android.support:support-v4:25.2.0
  | | |   +-com.android.support:support-compat:25.2.0
  | | |   | +-com.android.support:support-annotations:25.2.0
  | | |   |
  | | |   +-com.android.support:support-core-ui:25.2.0
  | | |   | +-com.android.support:support-annotations:25.2.0
  | | |   | +-com.android.support:support-compat:25.2.0
  | | |   |   +-com.android.support:support-annotations:25.2.0
  | | |   |   
  | | |   +-com.android.support:support-core-utils:25.2.0
  | | |   | +-com.android.support:support-annotations:25.2.0
  | | |   | +-com.android.support:support-compat:25.2.0
  | | |   |   +-com.android.support:support-annotations:25.2.0
  | | |   |   
  | | |   +-com.android.support:support-fragment:25.2.0
  | | |   | +-com.android.support:support-compat:25.2.0
  | | |   | | +-com.android.support:support-annotations:25.2.0
  | | |   | |
  | | |   | +-com.android.support:support-core-ui:25.2.0
  | | |   | | +-com.android.support:support-annotations:25.2.0
  | | |   | | +-com.android.support:support-compat:25.2.0
  | | |   | |   +-com.android.support:support-annotations:25.2.0
  | | |   | |   
  | | |   | +-com.android.support:support-core-utils:25.2.0
  | | |   | | +-com.android.support:support-annotations:25.2.0
  | | |   | | +-com.android.support:support-compat:25.2.0
  | | |   | |   +-com.android.support:support-annotations:25.2.0
  | | |   | |   
  | | |   | +-com.android.support:support-media-compat:25.2.0
  | | |   |   +-com.android.support:support-annotations:25.2.0
  | | |   |   +-com.android.support:support-compat:25.2.0
  | | |   |     +-com.android.support:support-annotations:25.2.0
  | | |   |     
  | | |   +-com.android.support:support-media-compat:25.2.0
  | | |     +-com.android.support:support-annotations:25.2.0
  | | |     +-com.android.support:support-compat:25.2.0
  | | |       +-com.android.support:support-annotations:25.2.0
  | | |       
  | | +-com.google.android.gms:play-services-tasks:10.2.0
  | |   +-com.google.android.gms:play-services-basement:10.2.0
  | |     +-com.android.support:support-v4:24.0.0 (evicted by: 25.2.0)
  | |     +-com.android.support:support-v4:25.2.0
  | |       +-com.android.support:support-compat:25.2.0
  | |       | +-com.android.support:support-annotations:25.2.0
  | |       |
  | |       +-com.android.support:support-core-ui:25.2.0
  | |       | +-com.android.support:support-annotations:25.2.0
  | |       | +-com.android.support:support-compat:25.2.0
  | |       |   +-com.android.support:support-annotations:25.2.0
  | |       |   
  | |       +-com.android.support:support-core-utils:25.2.0
  | |       | +-com.android.support:support-annotations:25.2.0
  | |       | +-com.android.support:support-compat:25.2.0
  | |       |   +-com.android.support:support-annotations:25.2.0
  | |       |   
  | |       +-com.android.support:support-fragment:25.2.0
  | |       | +-com.android.support:support-compat:25.2.0
  | |       | | +-com.android.support:support-annotations:25.2.0
  | |       | |
  | |       | +-com.android.support:support-core-ui:25.2.0
  | |       | | +-com.android.support:support-annotations:25.2.0
  | |       | | +-com.android.support:support-compat:25.2.0
  | |       | |   +-com.android.support:support-annotations:25.2.0
  | |       | |   
  | |       | +-com.android.support:support-core-utils:25.2.0
  | |       | | +-com.android.support:support-annotations:25.2.0
  | |       | | +-com.android.support:support-compat:25.2.0
  | |       | |   +-com.android.support:support-annotations:25.2.0
  | |       | |   
  | |       | +-com.android.support:support-media-compat:25.2.0
  | |       |   +-com.android.support:support-annotations:25.2.0
  | |       |   +-com.android.support:support-compat:25.2.0
  | |       |     +-com.android.support:support-annotations:25.2.0
  | |       |     
  | |       +-com.android.support:support-media-compat:25.2.0
  | |         +-com.android.support:support-annotations:25.2.0
  | |         +-com.android.support:support-compat:25.2.0
  | |           +-com.android.support:support-annotations:25.2.0
  | |           
  | +-io.monix:monix-eval_2.11:2.2.2 [S]
  | | +-io.monix:monix-execution_2.11:2.2.2 [S]
  | | | +-org.reactivestreams:reactive-streams:1.0.0
  | | |
  | | +-io.monix:monix-types_2.11:2.2.2 [S]
  | |
  | +-io.monix:monix-reactive_2.11:2.2.2 [S]
  | | +-io.monix:monix-eval_2.11:2.2.2 [S]
  | | | +-io.monix:monix-execution_2.11:2.2.2 [S]
  | | | | +-org.reactivestreams:reactive-streams:1.0.0
  | | | |
  | | | +-io.monix:monix-types_2.11:2.2.2 [S]
  | | |
  | | +-io.monix:monix-execution_2.11:2.2.2 [S]
  | | | +-org.reactivestreams:reactive-streams:1.0.0
  | | |
  | | +-io.monix:monix-types_2.11:2.2.2 [S]
  | | +-org.jctools:jctools-core:2.0
  | |
  | +-io.reactivex.rxjava2:rxjava:2.0.7
  | | +-org.reactivestreams:reactive-streams:1.0.0
  | |
  | +-io.reactivex:rxjava-reactive-streams:1.2.1
  | | +-io.reactivex:rxjava:1.2.2 (evicted by: 1.2.7)
  | | +-io.reactivex:rxjava:1.2.7
  | | +-org.reactivestreams:reactive-streams:1.0.0
  | |
  | +-io.reactivex:rxjava:1.2.2 (evicted by: 1.2.7)
  | +-io.reactivex:rxjava:1.2.7
  | +-io.taig.android:toolbelt-app_2.11:0.8.0 [S]
  | | +-com.android.support:support-v13:25.2.0
  | | | +-com.android.support:support-annotations:25.2.0
  | | | +-com.android.support:support-v4:25.2.0
  | | |   +-com.android.support:support-compat:25.2.0
  | | |   | +-com.android.support:support-annotations:25.2.0
  | | |   |
  | | |   +-com.android.support:support-core-ui:25.2.0
  | | |   | +-com.android.support:support-annotations:25.2.0
  | | |   | +-com.android.support:support-compat:25.2.0
  | | |   |   +-com.android.support:support-annotations:25.2.0
  | | |   |   
  | | |   +-com.android.support:support-core-utils:25.2.0
  | | |   | +-com.android.support:support-annotations:25.2.0
  | | |   | +-com.android.support:support-compat:25.2.0
  | | |   |   +-com.android.support:support-annotations:25.2.0
  | | |   |   
  | | |   +-com.android.support:support-fragment:25.2.0
  | | |   | +-com.android.support:support-compat:25.2.0
  | | |   | | +-com.android.support:support-annotations:25.2.0
  | | |   | |
  | | |   | +-com.android.support:support-core-ui:25.2.0
  | | |   | | +-com.android.support:support-annotations:25.2.0
  | | |   | | +-com.android.support:support-compat:25.2.0
  | | |   | |   +-com.android.support:support-annotations:25.2.0
  | | |   | |   
  | | |   | +-com.android.support:support-core-utils:25.2.0
  | | |   | | +-com.android.support:support-annotations:25.2.0
  | | |   | | +-com.android.support:support-compat:25.2.0
  | | |   | |   +-com.android.support:support-annotations:25.2.0
  | | |   | |   
  | | |   | +-com.android.support:support-media-compat:25.2.0
  | | |   |   +-com.android.support:support-annotations:25.2.0
  | | |   |   +-com.android.support:support-compat:25.2.0
  | | |   |     +-com.android.support:support-annotations:25.2.0
  | | |   |     
  | | |   +-com.android.support:support-media-compat:25.2.0
  | | |     +-com.android.support:support-annotations:25.2.0
  | | |     +-com.android.support:support-compat:25.2.0
  | | |       +-com.android.support:support-annotations:25.2.0
  | | |       
  | | +-com.android.support:support-v4:25.2.0
  | | | +-com.android.support:support-compat:25.2.0
  | | | | +-com.android.support:support-annotations:25.2.0
  | | | |
  | | | +-com.android.support:support-core-ui:25.2.0
  | | | | +-com.android.support:support-annotations:25.2.0
  | | | | +-com.android.support:support-compat:25.2.0
  | | | |   +-com.android.support:support-annotations:25.2.0
  | | | |   
  | | | +-com.android.support:support-core-utils:25.2.0
  | | | | +-com.android.support:support-annotations:25.2.0
  | | | | +-com.android.support:support-compat:25.2.0
  | | | |   +-com.android.support:support-annotations:25.2.0
  | | | |   
  | | | +-com.android.support:support-fragment:25.2.0
  | | | | +-com.android.support:support-compat:25.2.0
  | | | | | +-com.android.support:support-annotations:25.2.0
  | | | | |
  | | | | +-com.android.support:support-core-ui:25.2.0
  | | | | | +-com.android.support:support-annotations:25.2.0
  | | | | | +-com.android.support:support-compat:25.2.0
  | | | | |   +-com.android.support:support-annotations:25.2.0
  | | | | |   
  | | | | +-com.android.support:support-core-utils:25.2.0
  | | | | | +-com.android.support:support-annotations:25.2.0
  | | | | | +-com.android.support:support-compat:25.2.0
  | | | | |   +-com.android.support:support-annotations:25.2.0
  | | | | |   
  | | | | +-com.android.support:support-media-compat:25.2.0
  | | | |   +-com.android.support:support-annotations:25.2.0
  | | | |   +-com.android.support:support-compat:25.2.0
  | | | |     +-com.android.support:support-annotations:25.2.0
  | | | |     
  | | | +-com.android.support:support-media-compat:25.2.0
  | | |   +-com.android.support:support-annotations:25.2.0
  | | |   +-com.android.support:support-compat:25.2.0
  | | |     +-com.android.support:support-annotations:25.2.0
  | | |     
  | | +-io.taig.android:toolbelt-context_2.11:0.8.0 [S]
  | | | +-io.taig.android:toolbelt-log_2.11:0.8.0 [S]
  | | |
  | | +-io.taig.android:toolbelt-log_2.11:0.8.0 [S]
  | | +-io.taig.android:toolbelt-system-service_2.11:0.8.0 [S]
  | |
  | +-io.taig.android:toolbelt-log_2.11:0.8.0 [S]
  |
  +-io.taig.android:toolbelt-resource_2.11:0.8.0 [S]
  | +-com.android.support:support-v4:25.2.0
  | | +-com.android.support:support-compat:25.2.0
  | | | +-com.android.support:support-annotations:25.2.0
  | | |
  | | +-com.android.support:support-core-ui:25.2.0
  | | | +-com.android.support:support-annotations:25.2.0
  | | | +-com.android.support:support-compat:25.2.0
  | | |   +-com.android.support:support-annotations:25.2.0
  | | |   
  | | +-com.android.support:support-core-utils:25.2.0
  | | | +-com.android.support:support-annotations:25.2.0
  | | | +-com.android.support:support-compat:25.2.0
  | | |   +-com.android.support:support-annotations:25.2.0
  | | |   
  | | +-com.android.support:support-fragment:25.2.0
  | | | +-com.android.support:support-compat:25.2.0
  | | | | +-com.android.support:support-annotations:25.2.0
  | | | |
  | | | +-com.android.support:support-core-ui:25.2.0
  | | | | +-com.android.support:support-annotations:25.2.0
  | | | | +-com.android.support:support-compat:25.2.0
  | | | |   +-com.android.support:support-annotations:25.2.0
  | | | |   
  | | | +-com.android.support:support-core-utils:25.2.0
  | | | | +-com.android.support:support-annotations:25.2.0
  | | | | +-com.android.support:support-compat:25.2.0
  | | | |   +-com.android.support:support-annotations:25.2.0
  | | | |   
  | | | +-com.android.support:support-media-compat:25.2.0
  | | |   +-com.android.support:support-annotations:25.2.0
  | | |   +-com.android.support:support-compat:25.2.0
  | | |     +-com.android.support:support-annotations:25.2.0
  | | |     
  | | +-com.android.support:support-media-compat:25.2.0
  | |   +-com.android.support:support-annotations:25.2.0
  | |   +-com.android.support:support-compat:25.2.0
  | |     +-com.android.support:support-annotations:25.2.0
  | |     
  | +-io.taig.android:toolbelt-compatibility:0.8.0
  | | +-com.android.support:support-v4:25.2.0
  | |   +-com.android.support:support-compat:25.2.0
  | |   | +-com.android.support:support-annotations:25.2.0
  | |   |
  | |   +-com.android.support:support-core-ui:25.2.0
  | |   | +-com.android.support:support-annotations:25.2.0
  | |   | +-com.android.support:support-compat:25.2.0
  | |   |   +-com.android.support:support-annotations:25.2.0
  | |   |   
  | |   +-com.android.support:support-core-utils:25.2.0
  | |   | +-com.android.support:support-annotations:25.2.0
  | |   | +-com.android.support:support-compat:25.2.0
  | |   |   +-com.android.support:support-annotations:25.2.0
  | |   |   
  | |   +-com.android.support:support-fragment:25.2.0
  | |   | +-com.android.support:support-compat:25.2.0
  | |   | | +-com.android.support:support-annotations:25.2.0
  | |   | |
  | |   | +-com.android.support:support-core-ui:25.2.0
  | |   | | +-com.android.support:support-annotations:25.2.0
  | |   | | +-com.android.support:support-compat:25.2.0
  | |   | |   +-com.android.support:support-annotations:25.2.0
  | |   | |   
  | |   | +-com.android.support:support-core-utils:25.2.0
  | |   | | +-com.android.support:support-annotations:25.2.0
  | |   | | +-com.android.support:support-compat:25.2.0
  | |   | |   +-com.android.support:support-annotations:25.2.0
  | |   | |   
  | |   | +-com.android.support:support-media-compat:25.2.0
  | |   |   +-com.android.support:support-annotations:25.2.0
  | |   |   +-com.android.support:support-compat:25.2.0
  | |   |     +-com.android.support:support-annotations:25.2.0
  | |   |     
  | |   +-com.android.support:support-media-compat:25.2.0
  | |     +-com.android.support:support-annotations:25.2.0
  | |     +-com.android.support:support-compat:25.2.0
  | |       +-com.android.support:support-annotations:25.2.0
  | |       
  | +-io.taig.android:toolbelt-graphic_2.11:0.8.0 [S]
  |
  +-io.taig.android:toolbelt-system-service_2.11:0.8.0 [S]
  +-io.taig.android:toolbelt-unit_2.11:0.8.0 [S]
  +-io.taig.android:toolbelt-util_2.11:0.8.0 [S]
  +-io.taig.android:toolbelt-widget_2.11:0.8.0 [S]
    +-com.android.support:recyclerview-v7:25.2.0
    | +-com.android.support:support-annotations:25.2.0
    | +-com.android.support:support-compat:25.2.0
    | | +-com.android.support:support-annotations:25.2.0
    | |
    | +-com.android.support:support-core-ui:25.2.0
    |   +-com.android.support:support-annotations:25.2.0
    |   +-com.android.support:support-compat:25.2.0
    |     +-com.android.support:support-annotations:25.2.0
    |     
    +-com.android.support:support-v4:25.2.0
    | +-com.android.support:support-compat:25.2.0
    | | +-com.android.support:support-annotations:25.2.0
    | |
    | +-com.android.support:support-core-ui:25.2.0
    | | +-com.android.support:support-annotations:25.2.0
    | | +-com.android.support:support-compat:25.2.0
    | |   +-com.android.support:support-annotations:25.2.0
    | |   
    | +-com.android.support:support-core-utils:25.2.0
    | | +-com.android.support:support-annotations:25.2.0
    | | +-com.android.support:support-compat:25.2.0
    | |   +-com.android.support:support-annotations:25.2.0
    | |   
    | +-com.android.support:support-fragment:25.2.0
    | | +-com.android.support:support-compat:25.2.0
    | | | +-com.android.support:support-annotations:25.2.0
    | | |
    | | +-com.android.support:support-core-ui:25.2.0
    | | | +-com.android.support:support-annotations:25.2.0
    | | | +-com.android.support:support-compat:25.2.0
    | | |   +-com.android.support:support-annotations:25.2.0
    | | |   
    | | +-com.android.support:support-core-utils:25.2.0
    | | | +-com.android.support:support-annotations:25.2.0
    | | | +-com.android.support:support-compat:25.2.0
    | | |   +-com.android.support:support-annotations:25.2.0
    | | |   
    | | +-com.android.support:support-media-compat:25.2.0
    | |   +-com.android.support:support-annotations:25.2.0
    | |   +-com.android.support:support-compat:25.2.0
    | |     +-com.android.support:support-annotations:25.2.0
    | |     
    | +-com.android.support:support-media-compat:25.2.0
    |   +-com.android.support:support-annotations:25.2.0
    |   +-com.android.support:support-compat:25.2.0
    |     +-com.android.support:support-annotations:25.2.0
    |     
    +-io.taig.android:toolbelt-graphic_2.11:0.8.0 [S]
```