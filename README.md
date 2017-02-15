# Android Toolbelt

[![Circle CI](https://circleci.com/gh/Taig/toolbelt.svg?style=shield)](https://circleci.com/gh/Taig/toolbelt)
[![License](https://img.shields.io/badge/license-MIT-blue.svg)](https://raw.githubusercontent.com/Taig/toolbelt/master/LICENSE)

> A *Scala on Android* library providing essential helpers

## Install

```scala
libraryDependencies ++=
    "io.taig.android" %% "toolbelt-compatibility" % "0.5.19" ::
    "io.taig.android" %% "toolbelt-functional" % "0.5.19" ::
    "io.taig.android" %% "toolbelt-graphic" % "0.5.19" ::
    "io.taig.android" %% "toolbelt-intent" % "0.5.19" ::
    "io.taig.android" %% "toolbelt-log" % "0.5.19" ::
    "io.taig.android" %% "toolbelt-system-service" % "0.5.19" ::
    "io.taig.android" %% "toolbelt-unit" % "0.5.19" ::
    "io.taig.android" %% "toolbelt-util" % "0.5.19" ::
    "io.taig.android" %% "toolbelt-context" % "0.5.19" ::
    "io.taig.android" %% "toolbelt-widget" % "0.5.19" ::
    "io.taig.android" %% "toolbelt-concurrent" % "0.5.19" ::
    "io.taig.android" %% "toolbelt-resource" % "0.5.19" ::
    "io.taig.android" %% "toolbelt-app" % "0.5.19" ::
    Nil

libraryDependencies += "io.taig.android" %% "toolbelt" % "0.5.19"
```

```
io.taig.android:toolbelt_2.11:0.5.19 [S]
  +-io.taig.android:toolbelt-app_2.11:0.5.19 [S]
  | +-com.android.support:support-v13:24.2.0
  | | +-com.android.support:support-v4:24.2.0
  | |   +-com.android.support:support-compat:24.2.0
  | |   | +-com.android.support:support-annotations:24.2.0
  | |   | 
  | |   +-com.android.support:support-core-ui:24.2.0
  | |   | +-com.android.support:support-compat:24.2.0
  | |   |   +-com.android.support:support-annotations:24.2.0
  | |   |   
  | |   +-com.android.support:support-core-utils:24.2.0
  | |   | +-com.android.support:support-compat:24.2.0
  | |   |   +-com.android.support:support-annotations:24.2.0
  | |   |   
  | |   +-com.android.support:support-fragment:24.2.0
  | |   | +-com.android.support:support-compat:24.2.0
  | |   | | +-com.android.support:support-annotations:24.2.0
  | |   | | 
  | |   | +-com.android.support:support-core-ui:24.2.0
  | |   | | +-com.android.support:support-compat:24.2.0
  | |   | |   +-com.android.support:support-annotations:24.2.0
  | |   | |   
  | |   | +-com.android.support:support-core-utils:24.2.0
  | |   | | +-com.android.support:support-compat:24.2.0
  | |   | |   +-com.android.support:support-annotations:24.2.0
  | |   | |   
  | |   | +-com.android.support:support-media-compat:24.2.0
  | |   |   +-com.android.support:support-compat:24.2.0
  | |   |     +-com.android.support:support-annotations:24.2.0
  | |   |     
  | |   +-com.android.support:support-media-compat:24.2.0
  | |     +-com.android.support:support-compat:24.2.0
  | |       +-com.android.support:support-annotations:24.2.0
  | |       
  | +-com.android.support:support-v4:24.2.0
  | | +-com.android.support:support-compat:24.2.0
  | | | +-com.android.support:support-annotations:24.2.0
  | | | 
  | | +-com.android.support:support-core-ui:24.2.0
  | | | +-com.android.support:support-compat:24.2.0
  | | |   +-com.android.support:support-annotations:24.2.0
  | | |   
  | | +-com.android.support:support-core-utils:24.2.0
  | | | +-com.android.support:support-compat:24.2.0
  | | |   +-com.android.support:support-annotations:24.2.0
  | | |   
  | | +-com.android.support:support-fragment:24.2.0
  | | | +-com.android.support:support-compat:24.2.0
  | | | | +-com.android.support:support-annotations:24.2.0
  | | | | 
  | | | +-com.android.support:support-core-ui:24.2.0
  | | | | +-com.android.support:support-compat:24.2.0
  | | | |   +-com.android.support:support-annotations:24.2.0
  | | | |   
  | | | +-com.android.support:support-core-utils:24.2.0
  | | | | +-com.android.support:support-compat:24.2.0
  | | | |   +-com.android.support:support-annotations:24.2.0
  | | | |   
  | | | +-com.android.support:support-media-compat:24.2.0
  | | |   +-com.android.support:support-compat:24.2.0
  | | |     +-com.android.support:support-annotations:24.2.0
  | | |     
  | | +-com.android.support:support-media-compat:24.2.0
  | |   +-com.android.support:support-compat:24.2.0
  | |     +-com.android.support:support-annotations:24.2.0
  | |     
  | +-io.taig.android:toolbelt-context_2.11:0.5.19 [S]
  | | +-io.taig.android:toolbelt-log_2.11:0.5.19 [S]
  | | 
  | +-io.taig.android:toolbelt-log_2.11:0.5.19 [S]
  | +-io.taig.android:toolbelt-system-service_2.11:0.5.19 [S]
  | 
  +-io.taig.android:toolbelt-compatibility:0.5.19
  | +-com.android.support:support-v4:23.0.0 (evicted by: 24.2.0)
  | +-com.android.support:support-v4:24.2.0
  |   +-com.android.support:support-compat:24.2.0
  |   | +-com.android.support:support-annotations:24.2.0
  |   | 
  |   +-com.android.support:support-core-ui:24.2.0
  |   | +-com.android.support:support-compat:24.2.0
  |   |   +-com.android.support:support-annotations:24.2.0
  |   |   
  |   +-com.android.support:support-core-utils:24.2.0
  |   | +-com.android.support:support-compat:24.2.0
  |   |   +-com.android.support:support-annotations:24.2.0
  |   |   
  |   +-com.android.support:support-fragment:24.2.0
  |   | +-com.android.support:support-compat:24.2.0
  |   | | +-com.android.support:support-annotations:24.2.0
  |   | | 
  |   | +-com.android.support:support-core-ui:24.2.0
  |   | | +-com.android.support:support-compat:24.2.0
  |   | |   +-com.android.support:support-annotations:24.2.0
  |   | |   
  |   | +-com.android.support:support-core-utils:24.2.0
  |   | | +-com.android.support:support-compat:24.2.0
  |   | |   +-com.android.support:support-annotations:24.2.0
  |   | |   
  |   | +-com.android.support:support-media-compat:24.2.0
  |   |   +-com.android.support:support-compat:24.2.0
  |   |     +-com.android.support:support-annotations:24.2.0
  |   |     
  |   +-com.android.support:support-media-compat:24.2.0
  |     +-com.android.support:support-compat:24.2.0
  |       +-com.android.support:support-annotations:24.2.0
  |       
  +-io.taig.android:toolbelt-concurrent_2.11:0.5.19 [S]
  | +-io.monix:monix-eval_2.11:2.0-RC11 [S]
  | | +-io.monix:monix-execution_2.11:2.0-RC11 [S]
  | | | +-org.reactivestreams:reactive-streams:1.0.0
  | | | 
  | | +-io.monix:monix-types_2.11:2.0-RC11 [S]
  | |   +-com.github.mpilquist:simulacrum_2.11:0.8.0 [S]
  | |     +-org.typelevel:macro-compat_2.11:1.1.1 [S]
  | |     
  | +-io.taig.android:toolbelt-app_2.11:0.5.19 [S]
  | | +-com.android.support:support-v13:24.2.0
  | | | +-com.android.support:support-v4:24.2.0
  | | |   +-com.android.support:support-compat:24.2.0
  | | |   | +-com.android.support:support-annotations:24.2.0
  | | |   | 
  | | |   +-com.android.support:support-core-ui:24.2.0
  | | |   | +-com.android.support:support-compat:24.2.0
  | | |   |   +-com.android.support:support-annotations:24.2.0
  | | |   |   
  | | |   +-com.android.support:support-core-utils:24.2.0
  | | |   | +-com.android.support:support-compat:24.2.0
  | | |   |   +-com.android.support:support-annotations:24.2.0
  | | |   |   
  | | |   +-com.android.support:support-fragment:24.2.0
  | | |   | +-com.android.support:support-compat:24.2.0
  | | |   | | +-com.android.support:support-annotations:24.2.0
  | | |   | | 
  | | |   | +-com.android.support:support-core-ui:24.2.0
  | | |   | | +-com.android.support:support-compat:24.2.0
  | | |   | |   +-com.android.support:support-annotations:24.2.0
  | | |   | |   
  | | |   | +-com.android.support:support-core-utils:24.2.0
  | | |   | | +-com.android.support:support-compat:24.2.0
  | | |   | |   +-com.android.support:support-annotations:24.2.0
  | | |   | |   
  | | |   | +-com.android.support:support-media-compat:24.2.0
  | | |   |   +-com.android.support:support-compat:24.2.0
  | | |   |     +-com.android.support:support-annotations:24.2.0
  | | |   |     
  | | |   +-com.android.support:support-media-compat:24.2.0
  | | |     +-com.android.support:support-compat:24.2.0
  | | |       +-com.android.support:support-annotations:24.2.0
  | | |       
  | | +-com.android.support:support-v4:24.2.0
  | | | +-com.android.support:support-compat:24.2.0
  | | | | +-com.android.support:support-annotations:24.2.0
  | | | | 
  | | | +-com.android.support:support-core-ui:24.2.0
  | | | | +-com.android.support:support-compat:24.2.0
  | | | |   +-com.android.support:support-annotations:24.2.0
  | | | |   
  | | | +-com.android.support:support-core-utils:24.2.0
  | | | | +-com.android.support:support-compat:24.2.0
  | | | |   +-com.android.support:support-annotations:24.2.0
  | | | |   
  | | | +-com.android.support:support-fragment:24.2.0
  | | | | +-com.android.support:support-compat:24.2.0
  | | | | | +-com.android.support:support-annotations:24.2.0
  | | | | | 
  | | | | +-com.android.support:support-core-ui:24.2.0
  | | | | | +-com.android.support:support-compat:24.2.0
  | | | | |   +-com.android.support:support-annotations:24.2.0
  | | | | |   
  | | | | +-com.android.support:support-core-utils:24.2.0
  | | | | | +-com.android.support:support-compat:24.2.0
  | | | | |   +-com.android.support:support-annotations:24.2.0
  | | | | |   
  | | | | +-com.android.support:support-media-compat:24.2.0
  | | | |   +-com.android.support:support-compat:24.2.0
  | | | |     +-com.android.support:support-annotations:24.2.0
  | | | |     
  | | | +-com.android.support:support-media-compat:24.2.0
  | | |   +-com.android.support:support-compat:24.2.0
  | | |     +-com.android.support:support-annotations:24.2.0
  | | |     
  | | +-io.taig.android:toolbelt-context_2.11:0.5.19 [S]
  | | | +-io.taig.android:toolbelt-log_2.11:0.5.19 [S]
  | | | 
  | | +-io.taig.android:toolbelt-log_2.11:0.5.19 [S]
  | | +-io.taig.android:toolbelt-system-service_2.11:0.5.19 [S]
  | | 
  | +-io.taig.android:toolbelt-util_2.11:0.5.19 [S]
  | 
  +-io.taig.android:toolbelt-context_2.11:0.5.19 [S]
  | +-io.taig.android:toolbelt-log_2.11:0.5.19 [S]
  | 
  +-io.taig.android:toolbelt-functional_2.11:0.5.19 [S]
  | +-org.typelevel:cats-core_2.11:0.7.0 [S]
  | | +-com.github.mpilquist:simulacrum_2.11:0.8.0 [S]
  | | | +-org.typelevel:macro-compat_2.11:1.1.1 [S]
  | | | 
  | | +-org.typelevel:cats-kernel_2.11:0.7.0 [S]
  | | +-org.typelevel:cats-macros_2.11:0.7.0 [S]
  | | | +-com.github.mpilquist:simulacrum_2.11:0.8.0 [S]
  | | | | +-org.typelevel:macro-compat_2.11:1.1.1 [S]
  | | | | 
  | | | +-org.typelevel:machinist_2.11:0.4.1 [S]
  | | |   +-org.scala-lang:scala-reflect:2.11.8 [S]
  | | |   
  | | +-org.typelevel:machinist_2.11:0.4.1 [S]
  | |   +-org.scala-lang:scala-reflect:2.11.8 [S]
  | |   
  | +-org.typelevel:cats-kernel_2.11:0.7.0 [S]
  | +-org.typelevel:cats-macros_2.11:0.7.0 [S]
  |   +-com.github.mpilquist:simulacrum_2.11:0.8.0 [S]
  |   | +-org.typelevel:macro-compat_2.11:1.1.1 [S]
  |   | 
  |   +-org.typelevel:machinist_2.11:0.4.1 [S]
  |     +-org.scala-lang:scala-reflect:2.11.8 [S]
  |     
  +-io.taig.android:toolbelt-graphic_2.11:0.5.19 [S]
  +-io.taig.android:toolbelt-intent_2.11:0.5.19 [S]
  +-io.taig.android:toolbelt-log_2.11:0.5.19 [S]
  +-io.taig.android:toolbelt-play-services_2.11:0.5.19 [S]
  | +-com.google.android.gms:play-services-location:9.4.0
  | | +-com.google.android.gms:play-services-base:9.4.0
  | | | +-com.google.android.gms:play-services-basement:9.4.0
  | | | | +-com.android.support:support-v4:23.0.0 (evicted by: 24.2.0)
  | | | | +-com.android.support:support-v4:24.2.0
  | | | |   +-com.android.support:support-compat:24.2.0
  | | | |   | +-com.android.support:support-annotations:24.2.0
  | | | |   | 
  | | | |   +-com.android.support:support-core-ui:24.2.0
  | | | |   | +-com.android.support:support-compat:24.2.0
  | | | |   |   +-com.android.support:support-annotations:24.2.0
  | | | |   |   
  | | | |   +-com.android.support:support-core-utils:24.2.0
  | | | |   | +-com.android.support:support-compat:24.2.0
  | | | |   |   +-com.android.support:support-annotations:24.2.0
  | | | |   |   
  | | | |   +-com.android.support:support-fragment:24.2.0
  | | | |   | +-com.android.support:support-compat:24.2.0
  | | | |   | | +-com.android.support:support-annotations:24.2.0
  | | | |   | | 
  | | | |   | +-com.android.support:support-core-ui:24.2.0
  | | | |   | | +-com.android.support:support-compat:24.2.0
  | | | |   | |   +-com.android.support:support-annotations:24.2.0
  | | | |   | |   
  | | | |   | +-com.android.support:support-core-utils:24.2.0
  | | | |   | | +-com.android.support:support-compat:24.2.0
  | | | |   | |   +-com.android.support:support-annotations:24.2.0
  | | | |   | |   
  | | | |   | +-com.android.support:support-media-compat:24.2.0
  | | | |   |   +-com.android.support:support-compat:24.2.0
  | | | |   |     +-com.android.support:support-annotations:24.2.0
  | | | |   |     
  | | | |   +-com.android.support:support-media-compat:24.2.0
  | | | |     +-com.android.support:support-compat:24.2.0
  | | | |       +-com.android.support:support-annotations:24.2.0
  | | | |       
  | | | +-com.google.android.gms:play-services-tasks:9.4.0
  | | |   +-com.google.android.gms:play-services-basement:9.4.0
  | | |     +-com.android.support:support-v4:23.0.0 (evicted by: 24.2.0)
  | | |     +-com.android.support:support-v4:24.2.0
  | | |       +-com.android.support:support-compat:24.2.0
  | | |       | +-com.android.support:support-annotations:24.2.0
  | | |       | 
  | | |       +-com.android.support:support-core-ui:24.2.0
  | | |       | +-com.android.support:support-compat:24.2.0
  | | |       |   +-com.android.support:support-annotations:24.2.0
  | | |       |   
  | | |       +-com.android.support:support-core-utils:24.2.0
  | | |       | +-com.android.support:support-compat:24.2.0
  | | |       |   +-com.android.support:support-annotations:24.2.0
  | | |       |   
  | | |       +-com.android.support:support-fragment:24.2.0
  | | |       | +-com.android.support:support-compat:24.2.0
  | | |       | | +-com.android.support:support-annotations:24.2.0
  | | |       | | 
  | | |       | +-com.android.support:support-core-ui:24.2.0
  | | |       | | +-com.android.support:support-compat:24.2.0
  | | |       | |   +-com.android.support:support-annotations:24.2.0
  | | |       | |   
  | | |       | +-com.android.support:support-core-utils:24.2.0
  | | |       | | +-com.android.support:support-compat:24.2.0
  | | |       | |   +-com.android.support:support-annotations:24.2.0
  | | |       | |   
  | | |       | +-com.android.support:support-media-compat:24.2.0
  | | |       |   +-com.android.support:support-compat:24.2.0
  | | |       |     +-com.android.support:support-annotations:24.2.0
  | | |       |     
  | | |       +-com.android.support:support-media-compat:24.2.0
  | | |         +-com.android.support:support-compat:24.2.0
  | | |           +-com.android.support:support-annotations:24.2.0
  | | |           
  | | +-com.google.android.gms:play-services-basement:9.4.0
  | |   +-com.android.support:support-v4:23.0.0 (evicted by: 24.2.0)
  | |   +-com.android.support:support-v4:24.2.0
  | |     +-com.android.support:support-compat:24.2.0
  | |     | +-com.android.support:support-annotations:24.2.0
  | |     | 
  | |     +-com.android.support:support-core-ui:24.2.0
  | |     | +-com.android.support:support-compat:24.2.0
  | |     |   +-com.android.support:support-annotations:24.2.0
  | |     |   
  | |     +-com.android.support:support-core-utils:24.2.0
  | |     | +-com.android.support:support-compat:24.2.0
  | |     |   +-com.android.support:support-annotations:24.2.0
  | |     |   
  | |     +-com.android.support:support-fragment:24.2.0
  | |     | +-com.android.support:support-compat:24.2.0
  | |     | | +-com.android.support:support-annotations:24.2.0
  | |     | | 
  | |     | +-com.android.support:support-core-ui:24.2.0
  | |     | | +-com.android.support:support-compat:24.2.0
  | |     | |   +-com.android.support:support-annotations:24.2.0
  | |     | |   
  | |     | +-com.android.support:support-core-utils:24.2.0
  | |     | | +-com.android.support:support-compat:24.2.0
  | |     | |   +-com.android.support:support-annotations:24.2.0
  | |     | |   
  | |     | +-com.android.support:support-media-compat:24.2.0
  | |     |   +-com.android.support:support-compat:24.2.0
  | |     |     +-com.android.support:support-annotations:24.2.0
  | |     |     
  | |     +-com.android.support:support-media-compat:24.2.0
  | |       +-com.android.support:support-compat:24.2.0
  | |         +-com.android.support:support-annotations:24.2.0
  | |         
  | +-io.monix:monix-reactive_2.11:2.0-RC11 [S]
  | | +-io.monix:monix-eval_2.11:2.0-RC11 [S]
  | | | +-io.monix:monix-execution_2.11:2.0-RC11 [S]
  | | | | +-org.reactivestreams:reactive-streams:1.0.0
  | | | | 
  | | | +-io.monix:monix-types_2.11:2.0-RC11 [S]
  | | |   +-com.github.mpilquist:simulacrum_2.11:0.8.0 [S]
  | | |     +-org.typelevel:macro-compat_2.11:1.1.1 [S]
  | | |     
  | | +-io.monix:monix-execution_2.11:2.0-RC11 [S]
  | | | +-org.reactivestreams:reactive-streams:1.0.0
  | | | 
  | | +-io.monix:monix-types_2.11:2.0-RC11 [S]
  | |   +-com.github.mpilquist:simulacrum_2.11:0.8.0 [S]
  | |     +-org.typelevel:macro-compat_2.11:1.1.1 [S]
  | |     
  | +-io.taig.android:toolbelt-log_2.11:0.5.19 [S]
  | 
  +-io.taig.android:toolbelt-resource_2.11:0.5.19 [S]
  | +-com.android.support:support-v4:24.2.0
  | | +-com.android.support:support-compat:24.2.0
  | | | +-com.android.support:support-annotations:24.2.0
  | | | 
  | | +-com.android.support:support-core-ui:24.2.0
  | | | +-com.android.support:support-compat:24.2.0
  | | |   +-com.android.support:support-annotations:24.2.0
  | | |   
  | | +-com.android.support:support-core-utils:24.2.0
  | | | +-com.android.support:support-compat:24.2.0
  | | |   +-com.android.support:support-annotations:24.2.0
  | | |   
  | | +-com.android.support:support-fragment:24.2.0
  | | | +-com.android.support:support-compat:24.2.0
  | | | | +-com.android.support:support-annotations:24.2.0
  | | | | 
  | | | +-com.android.support:support-core-ui:24.2.0
  | | | | +-com.android.support:support-compat:24.2.0
  | | | |   +-com.android.support:support-annotations:24.2.0
  | | | |   
  | | | +-com.android.support:support-core-utils:24.2.0
  | | | | +-com.android.support:support-compat:24.2.0
  | | | |   +-com.android.support:support-annotations:24.2.0
  | | | |   
  | | | +-com.android.support:support-media-compat:24.2.0
  | | |   +-com.android.support:support-compat:24.2.0
  | | |     +-com.android.support:support-annotations:24.2.0
  | | |     
  | | +-com.android.support:support-media-compat:24.2.0
  | |   +-com.android.support:support-compat:24.2.0
  | |     +-com.android.support:support-annotations:24.2.0
  | |     
  | +-io.taig.android:toolbelt-compatibility:0.5.19
  | | +-com.android.support:support-v4:23.0.0 (evicted by: 24.2.0)
  | | +-com.android.support:support-v4:24.2.0
  | |   +-com.android.support:support-compat:24.2.0
  | |   | +-com.android.support:support-annotations:24.2.0
  | |   | 
  | |   +-com.android.support:support-core-ui:24.2.0
  | |   | +-com.android.support:support-compat:24.2.0
  | |   |   +-com.android.support:support-annotations:24.2.0
  | |   |   
  | |   +-com.android.support:support-core-utils:24.2.0
  | |   | +-com.android.support:support-compat:24.2.0
  | |   |   +-com.android.support:support-annotations:24.2.0
  | |   |   
  | |   +-com.android.support:support-fragment:24.2.0
  | |   | +-com.android.support:support-compat:24.2.0
  | |   | | +-com.android.support:support-annotations:24.2.0
  | |   | | 
  | |   | +-com.android.support:support-core-ui:24.2.0
  | |   | | +-com.android.support:support-compat:24.2.0
  | |   | |   +-com.android.support:support-annotations:24.2.0
  | |   | |   
  | |   | +-com.android.support:support-core-utils:24.2.0
  | |   | | +-com.android.support:support-compat:24.2.0
  | |   | |   +-com.android.support:support-annotations:24.2.0
  | |   | |   
  | |   | +-com.android.support:support-media-compat:24.2.0
  | |   |   +-com.android.support:support-compat:24.2.0
  | |   |     +-com.android.support:support-annotations:24.2.0
  | |   |     
  | |   +-com.android.support:support-media-compat:24.2.0
  | |     +-com.android.support:support-compat:24.2.0
  | |       +-com.android.support:support-annotations:24.2.0
  | |       
  | +-io.taig.android:toolbelt-graphic_2.11:0.5.19 [S]
  | 
  +-io.taig.android:toolbelt-system-service_2.11:0.5.19 [S]
  +-io.taig.android:toolbelt-unit_2.11:0.5.19 [S]
  +-io.taig.android:toolbelt-util_2.11:0.5.19 [S]
  +-io.taig.android:toolbelt-widget_2.11:0.5.19 [S]
    +-com.android.support:recyclerview-v7:24.2.0
    | +-com.android.support:support-annotations:24.2.0
    | +-com.android.support:support-compat:24.2.0
    | | +-com.android.support:support-annotations:24.2.0
    | | 
    | +-com.android.support:support-core-ui:24.2.0
    |   +-com.android.support:support-compat:24.2.0
    |     +-com.android.support:support-annotations:24.2.0
    |     
    +-com.android.support:support-v4:24.2.0
    | +-com.android.support:support-compat:24.2.0
    | | +-com.android.support:support-annotations:24.2.0
    | | 
    | +-com.android.support:support-core-ui:24.2.0
    | | +-com.android.support:support-compat:24.2.0
    | |   +-com.android.support:support-annotations:24.2.0
    | |   
    | +-com.android.support:support-core-utils:24.2.0
    | | +-com.android.support:support-compat:24.2.0
    | |   +-com.android.support:support-annotations:24.2.0
    | |   
    | +-com.android.support:support-fragment:24.2.0
    | | +-com.android.support:support-compat:24.2.0
    | | | +-com.android.support:support-annotations:24.2.0
    | | | 
    | | +-com.android.support:support-core-ui:24.2.0
    | | | +-com.android.support:support-compat:24.2.0
    | | |   +-com.android.support:support-annotations:24.2.0
    | | |   
    | | +-com.android.support:support-core-utils:24.2.0
    | | | +-com.android.support:support-compat:24.2.0
    | | |   +-com.android.support:support-annotations:24.2.0
    | | |   
    | | +-com.android.support:support-media-compat:24.2.0
    | |   +-com.android.support:support-compat:24.2.0
    | |     +-com.android.support:support-annotations:24.2.0
    | |     
    | +-com.android.support:support-media-compat:24.2.0
    |   +-com.android.support:support-compat:24.2.0
    |     +-com.android.support:support-annotations:24.2.0
    |     
    +-io.taig.android:toolbelt-graphic_2.11:0.5.19 [S]
```