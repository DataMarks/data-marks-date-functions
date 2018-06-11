import scala.util.Try
lazy val `data-marks-date-functions` = project in file(".")
organization :="org.datamarks"
name := "data-marks-date-functions"
scalaVersion := "2.11.12"
crossPaths := false
publishArtifact in (Compile, packageDoc) := false
publishArtifact in (Compile, packageSrc) := false

lazy val pwd =  Try(scala.sys.env("ARTIFACTORY_PASSWORD")).getOrElse("")
lazy val usr =  Try(scala.sys.env("ARTIFACTORY_USERNAME")).getOrElse("")
lazy val credentialsPath = Path.userHome / ".sbt" / ".credentials"
credentials +=  {
  if (usr.isEmpty)  Credentials(credentialsPath)
  else Credentials("Artifactory Realm", "artifactory.sicredi.io", usr, pwd)
}
publishConfiguration := publishConfiguration.value.withOverwrite(true)
publishTo := {
  if (isSnapshot.value) Some("snapshots" at  "https://artifactory.sicredi.io/artifactory/snapshots/")
  else   Some("releases"  at  "https://artifactory.sicredi.io/artifactory/releases/")
}

libraryDependencies ++= Seq(
  "org.slf4j"          		  % "slf4j-api"                % "1.7.5",
  "org.slf4j"          		  % "slf4j-log4j12"            % "1.7.5",
  "log4j"              		  % "log4j"                    % "1.2.17",
  "org.perf4j"         		  % "perf4j"                   % "0.9.16",
  "org.apache.commons" 		  % "commons-lang3"            % "3.4",
  "com.typesafe"            % "config"                   % "1.3.1"
)
libraryDependencies += "org.scalatest"   %% "scalatest"  % "3.0.5"  % "test"
unmanagedSourceDirectories in Test += baseDirectory.value / "src" / "test" / "integration"