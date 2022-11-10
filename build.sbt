name := """short-url"""
organization := "com.ugurdonmez"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.13.10"

libraryDependencies += guice
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "5.0.0" % Test
libraryDependencies += "org.apache.commons" % "commons-parent" % "54"
libraryDependencies += "commons-validator" % "commons-validator" % "1.7"

libraryDependencies ++= Seq(
  "net.debasishg" %% "redisclient" % "3.41"
)

libraryDependencies ++= Seq(
  "org.scalatestplus" %% "mockito-3-4" % "3.2.10.0" % Test,
)


val circeVersion = "0.14.1"

libraryDependencies ++= Seq(
  "io.circe" %% "circe-core",
  "io.circe" %% "circe-generic",
  "io.circe" %% "circe-parser"
).map(_ % circeVersion)


enablePlugins(JavaAppPackaging)


// Adds additional packages into Twirl
//TwirlKeys.templateImports += "com.ugurdonmez.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "com.ugurdonmez.binders._"
