name := "fp-in-scala"

version := "0.1"

scalaVersion := "2.11.7"

libraryDependencies += "org.scalatest" % "scalatest_2.11" % "2.2.4" % "test"

libraryDependencies += "org.scalacheck" %% "scalacheck" % "1.12.4" % "test"

scalacOptions ++= Seq(
  "-deprecation",
  "-encoding", "UTF-8",
  "-explaintypes",
  "-feature",
  "-unchecked",
  "-Xfatal-warnings",
  "-Xfuture",
  "-Xlint",
//  "-Xlog-implicits",
  "-Yno-adapted-args",
  "-Ywarn-numeric-widen",
  "-Ywarn-unused-import",
  "-Ywarn-value-discard"
)
