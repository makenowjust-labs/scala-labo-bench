Global / onChangedBuildSource := ReloadOnSourceChanges

ThisBuild / githubOwner := "MakeNowJust-Labo"
ThisBuild / githubRepository := "scala-labo-bench"

ThisBuild / scalaVersion := "2.13.3"
ThisBuild / scalacOptions ++= Seq(
  "-encoding",
  "UTF-8",
  "-feature",
  "-deprecation"
)

lazy val root = project
  .in(file("."))
  .aggregate(parser)

lazy val parser = project
  .in(file("benchmarks/parser"))
  .settings(
    console / initialCommands := """
      |import codes.quine.labo.bench.parser._
      """.stripMargin,
    scalacOptions ++= Seq(
      "-opt:l:inline",
      "-opt-inline-from:codes.quine.labo.miniparse.*",
      "-opt-warnings"
    ),
    // Dependencies:
    libraryDependencies += "codes.quine.labo" %% "miniparse" % "0.1.0",
    libraryDependencies += "codes.quine.labo" %% "stackparse" % "0.1.0",
    libraryDependencies += "com.lihaoyi" %% "fastparse" % "2.2.2",
    libraryDependencies += "org.scala-lang.modules" %% "scala-parser-combinators" % "1.1.2",
    libraryDependencies += "org.tpolecat" %% "atto-core" % "0.7.0"
  )
  .enablePlugins(JmhPlugin)