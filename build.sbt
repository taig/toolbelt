lazy val toolbelt = project.in( file( "." ) )
    .enablePlugins( AndroidLib )
    .settings( Settings.common ++ Settings.android )
    .settings(
        name := "toolbelt",
        normalizedName := name.value
    )
    .dependsOn( compatibility, functional, graphic, intent, log, monix, systemService, unit, util, context, widget, concurrent, resource, app )
    .aggregate( compatibility, functional, graphic, intent, log, monix, systemService, unit, util, context, widget, concurrent, resource, app )

lazy val compatibility = project
    .enablePlugins( AndroidJar )
    .settings( Settings.common ++ Settings.android )
    .settings(
        autoScalaLibrary := false,
        libraryDependencies ++=
            Settings.dependency.androidSupportV4 ::
            Nil
    )

lazy val functional = project
    .settings( Settings.common )
    .settings(
        exportJars := true,
        libraryDependencies ++=
            Settings.dependency.catsCore ::
            Settings.dependency.catsMacros ::
            Settings.dependency.catsKernel ::
            Nil
    )

lazy val graphic = project
    .enablePlugins( AndroidJar )
    .settings( Settings.common ++ Settings.android )

lazy val intent = project
    .enablePlugins( AndroidJar )
    .settings( Settings.common ++ Settings.android )

lazy val log = project
    .enablePlugins( AndroidJar )
    .settings( Settings.common ++ Settings.android )

lazy val monix = project
    .enablePlugins( AndroidJar )
    .settings( Settings.common ++ Settings.android )
    .settings(
        libraryDependencies ++=
            Settings.dependency.playServicesBase ::
            Settings.dependency.playServicesLocation ::
            Settings.dependency.monixEval ::
            Settings.dependency.monixReactive ::
            Settings.dependency.rxJava ::
            Settings.dependency.rxJavaReactiveStream ::
            Settings.dependency.rxJava2 ::
            Nil
    )
    .dependsOn( app, log )

lazy val systemService = project.in( file( "system-service" ) )
    .enablePlugins( AndroidJar )
    .settings( Settings.common ++ Settings.android )
    .settings(
        name := "system-service"
    )

lazy val unit = project
    .enablePlugins( AndroidLib )
    .settings( Settings.common ++ Settings.android )

lazy val util = project
    .enablePlugins( AndroidJar )
    .settings( Settings.common ++ Settings.android )

lazy val context = project
    .enablePlugins( AndroidJar )
    .settings( Settings.common ++ Settings.android )
    .dependsOn( log )

lazy val widget = project
    .enablePlugins( AndroidJar )
    .settings( Settings.common ++ Settings.android )
    .settings(
        libraryDependencies ++=
            Settings.dependency.androidSupportRecycler ::
            Settings.dependency.androidSupportV4 ::
            Nil
    )
    .dependsOn( graphic )

lazy val concurrent = project
    .enablePlugins( AndroidJar )
    .settings( Settings.common ++ Settings.android )
    .settings(
        libraryDependencies ++=
            Settings.dependency.monixEval ::
            Settings.dependency.playServicesBase ::
            Nil
    )
    .dependsOn( app, util )

lazy val resource = project
    .enablePlugins( AndroidJar )
    .settings( Settings.common ++ Settings.android )
    .settings(
        libraryDependencies ++=
        Settings.dependency.androidSupportV4 ::
        Nil
    )
    .dependsOn( compatibility, graphic )

lazy val app = project
    .enablePlugins( AndroidJar )
    .settings( Settings.common ++ Settings.android )
    .settings(
        libraryDependencies ++=
            Settings.dependency.androidSupportV4 ::
            Settings.dependency.androidSupportV13 ::
            Nil
    )
    .dependsOn( context, log, systemService )