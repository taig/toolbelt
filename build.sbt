lazy val toolbelt = project.in( file( "." ) )
    .settings( Settings.common: _* )
    .settings(
        name := "Toolbelt"
    )
    .aggregate( core, concurrent )

lazy val core = project
    .settings( androidBuildAar ++ Settings.common ++ Settings.android: _* )
    .settings(
        libraryDependencies ++=
            "com.android.support" % "recyclerview-v7" % "24.0.0" ::
            "com.android.support" % "support-v4" % "24.0.0" ::
            "com.android.support" % "support-v13" % "24.0.0" ::
            Nil
    )

lazy val concurrent = project
    .settings( androidBuildAar ++ Settings.common ++ Settings.android: _* )
    .settings(
        libraryDependencies ++=
            "io.monix" %% "monix" % "2.0-RC7" ::
            Nil
    )
    .dependsOn( core )