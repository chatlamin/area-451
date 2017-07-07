name := "area-451"

maintainer := "Alexander Fefelov <alexanderfefelov@yandex.ru>"

scalaVersion := "2.12.2"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

resolvers ++= Seq(
  Resolver.jcenterRepo
)

libraryDependencies ++= Seq(
  guice,
  filters,
  "com.mohiva" %% "play-html-compressor" % "0.7.0",
  "nl.grons" %% "metrics-scala" % "3.5.9_a2.4",
  "io.dropwizard.metrics" % "metrics-graphite" % "3.1.2",
  "org.mpierce.metrics.reservoir" % "hdrhistogram-metrics-reservoir" % "1.1.2",
  "org.webjars" % "bootstrap" % "3.3.7"
)

doc in Compile := target.map(_ / "none").value

//--- sbt-native-packager: Docker

packageName in Docker := "alexanderfefelov/area-451"
dockerBaseImage := "openjdk:8-jre"
dockerExposedPorts := Seq(9000)
dockerExposedVolumes := Seq(
  "/opt/docker/conf",
  "/opt/docker/logs"
)

//--- sbt-native-packager: Debian

packageSummary in Debian := "area-451 application files"
packageDescription in Debian := "Сервер со страницей-заглушкой для использования российскими интернет-провайдерами, работающими по законам 139-ФЗ, 149-ФЗ, 187-ФЗ"

//--- sbt-native-packager: RPM

rpmVendor := "Alexander Fefelov <alexanderfefelov@yandex.ru>"
packageSummary in Rpm := "area-451 application files"
packageDescription in Rpm := "Сервер со страницей-заглушкой для использования российскими интернет-провайдерами, работающими по законам 139-ФЗ, 149-ФЗ, 187-ФЗ"
rpmLicense := Some("MIT License")
version in Rpm := version.value.replaceAll("-", "_")
