lazy val toolbelt = project.in( file( "." ) )
    .settings( Settings.common )
    .settings(
        name := "Toolbelt"
    )
    .dependsOn( log, intent, core, concurrent, functional )
    .aggregate( log, intent, core, concurrent, functional )

lazy val log = project
    .settings( androidBuildAar ++ Settings.common ++ Settings.android: _* )

lazy val intent = project
    .settings( androidBuildAar ++ Settings.common ++ Settings.android: _* )

lazy val core = project
    .settings( androidBuildAar ++ Settings.common ++ Settings.android: _* )
    .settings(
        libraryDependencies ++=
            "com.android.support" % "recyclerview-v7" % "24.0.0" ::
            "com.android.support" % "support-v4" % "24.0.0" ::
            "com.android.support" % "support-v13" % "24.0.0" ::
            Nil
    )
    .dependsOn( log, intent )

lazy val concurrent = project
    .settings( androidBuildAar ++ Settings.common ++ Settings.android: _* )
    .settings(
        libraryDependencies ++=
            "io.monix" %% "monix" % "2.0-RC8" ::
            Nil
    )
    .dependsOn( core )

lazy val functional = project
    .settings( Settings.common )
    .settings(
        libraryDependencies ++=
            "org.typelevel" %% "cats-core" % "0.6.0" ::
            "org.typelevel" %% "cats-macros" % "0.6.0" ::
            "org.typelevel" %% "cats-kernel" % "0.6.0" ::
            Nil
    )