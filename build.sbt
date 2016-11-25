name := "area-451"

scalaVersion := "2.11.7"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

libraryDependencies ++= Seq(
  filters,
  "com.mohiva" %% "play-html-compressor" % "0.6.3"
)

doc in Compile := target.map(_ / "none").value

//--- sbt-native-packager: Docker

maintainer in Docker := "Alexander Fefelov <alexanderfefelov@yandex.ru>"
packageName in Docker := "alexanderfefelov/area-451"
dockerBaseImage := "openjdk:8-jre"
dockerExposedPorts := Seq(9000)
dockerExposedVolumes in Docker := Seq(
  "/opt/docker/conf",
  "/opt/docker/public",
  "/opt/docker/logs"
)
