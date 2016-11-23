name := "area-451"

scalaVersion := "2.11.7"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

libraryDependencies ++= Seq(
  filters,
  "com.mohiva" %% "play-html-compressor" % "0.6.3"
)

doc in Compile <<= target.map(_ / "none")
