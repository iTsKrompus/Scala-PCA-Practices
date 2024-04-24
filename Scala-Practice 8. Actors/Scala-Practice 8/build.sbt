ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.13"

lazy val root = (project in file("."))
  .settings(
    name := "Scala-Practice 8"
  )
libraryDependencies += "com.typesafe.akka" %% "akka-actor" % "2.8.5"
