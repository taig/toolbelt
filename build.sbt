lazy val toolbelt = project.in( file( "." ) )
    .settings( androidBuildAar ++ Settings.common ++ Settings.android )
    .settings(
        name := "toolbelt",
        normalizedName := name.value
    )
    .dependsOn( compatibility, functional, graphic, intent, log, systemService, unit, util, context, monix, widget, concurrent, resource, app )
    .aggregate( compatibility, functional, graphic, intent, log, systemService, unit, util, context, monix, widget, concurrent, resource, app )

lazy val compatibility = project
    .settings( androidBuildAar ++ Settings.common ++ Settings.android )
    .settings(
        autoScalaLibrary := false,
        libraryDependencies ++=
            Settings.dependency.androidSupportV4 ::
            Nil
    )

lazy val functional = project
    .settings( Settings.common )
    .settings(
        libraryDependencies ++=
            "org.typelevel" %% "cats-core" % Settings.dependency.cats ::
            "org.typelevel" %% "cats-macros" % Settings.dependency.cats ::
            "org.typelevel" %% "cats-kernel" % Settings.dependency.cats ::
            Nil
    )

lazy val graphic = project
    .settings( androidBuildAar ++ Settings.common ++ Settings.android )

lazy val intent = project
    .settings( androidBuildAar ++ Settings.common ++ Settings.android )

lazy val log = project
    .settings( androidBuildAar ++ Settings.common ++ Settings.android )

lazy val systemService = project.in( file( "system-service" ) )
    .settings( androidBuildAar ++ Settings.common ++ Settings.android )
    .settings(
        name := "system-service"
    )

lazy val unit = project
    .settings( androidBuildAar ++ Settings.common ++ Settings.android )

lazy val util = project
    .settings( androidBuildAar ++ Settings.common ++ Settings.android )

lazy val context = project
    .settings( androidBuildAar ++ Settings.common ++ Settings.android )
    .dependsOn( log )

lazy val monix = project
    .settings( androidBuildAar ++ Settings.common ++ Settings.android )
    .settings(
        libraryDependencies ++=
            Settings.dependency.playServicesBase % "optional" ::
            Settings.dependency.playServicesLocation % "optional" ::
            Settings.dependency.monixEval ::
            Settings.dependency.monixReactive ::
            Nil
    )
    .dependsOn( log )

lazy val widget = project
    .settings( androidBuildAar ++ Settings.common ++ Settings.android )
    .settings(
        libraryDependencies ++=
            Settings.dependency.androidSupportRecycler ::
            Settings.dependency.androidSupportV4 ::
            Nil
    )
    .dependsOn( graphic )

lazy val concurrent = project
    .settings( androidBuildAar ++ Settings.common ++ Settings.android )
    .settings(
        libraryDependencies ++=
            Settings.dependency.monixEval ::
            Nil
    )
    .dependsOn( app, util )

lazy val resource = project
    .settings( androidBuildAar ++ Settings.common ++ Settings.android )
    .settings(
        libraryDependencies ++=
        Settings.dependency.androidSupportV4 ::
        Nil
    )
    .dependsOn( compatibility, graphic )

lazy val app = project
    .settings( androidBuildAar ++ Settings.common ++ Settings.android )
    .settings(
        libraryDependencies ++=
            Settings.dependency.androidSupportV4 ::
            Settings.dependency.androidSupportV13 ::
            Nil
    )
    .dependsOn( context, log, systemService )