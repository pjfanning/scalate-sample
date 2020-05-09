name := "scalate-sample"

version := "1.0"

scalaVersion := "2.12.11"

libraryDependencies ++= Seq(
  "org.scalatra.scalate" %% "scalate-core" % "1.9.5",
  "ch.qos.logback" % "logback-classic" % "1.2.3" % Runtime
)

