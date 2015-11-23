val id = "toolbelt"

description := "Essential helpers for Scala on Android"

homepage := Some( url( s"https://github.com/taig/$id" ) )

licenses := Seq( "MIT" -> url( s"https://raw.githubusercontent.com/taig/$id/master/LICENSE" ) )

organizationHomepage := Some( url( "http://taig.io" ) )

pomExtra := {
    <issueManagement>
        <url>https://github.com/taig/{id}/issues</url>
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
        url( s"https://github.com/taig/$id" ),
        s"scm:git:git://github.com/taig/$id.git",
        Some( s"scm:git:git@github.com:taig/$id.git" )
    )
)

sonatypeProfileName := "io.taig"

startYear := Some( 2014 )