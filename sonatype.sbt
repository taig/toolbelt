description := "Essential helpers for Scala on Android"

homepage := Some( url( "https://github.com/taig/toolbelt" ) )

licenses := Seq( "MIT" -> url( "https://raw.githubusercontent.com/taig/toolbelt/master/LICENSE" ) )

organizationHomepage := Some( url( "http://taig.io" ) )

pomExtra := {
    <issueManagement>
        <url>https://github.com/taig/toolbelt/issues</url>
        <system>GitHub Issues</system>
    </issueManagement>
    <developers>
        <developer>
            <id>Taig</id>
            <name>Niklas Klein</name>
            <email>mail@taig.io</email>
            <url>http://taig.io/</url>
        </developer>
    </developers>
}

pomIncludeRepository := { _ => false }

publishArtifact in Test := false

publishMavenStyle := true

publishTo <<= version ( version => {
    val url = Some( "https://oss.sonatype.org/" )

    if( version.endsWith( "SNAPSHOT" ) ) {
        url.map( "snapshot" at _ + "content/repositories/snapshots" )
    }
    else {
        url.map( "release" at _ + "service/local/staging/deploy/maven2" )
    }
} )

scmInfo := Some(
    ScmInfo(
        url( "https://github.com/taig/toolbelt" ),
        "scm:git:git://github.com/taig/toolbelt.git",
        Some( "scm:git:git@github.com:taig/toolbelt.git" )
    )
)

sonatypeProfileName := "io.taig"

startYear := Some( 2014 )