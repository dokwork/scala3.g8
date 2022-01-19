ThisBuild / organization := "$organization$"
ThisBuild / scalaVersion := "$scala_version$"

ThisBuild / semanticdbEnabled := true
ThisBuild / semanticdbVersion := scalafixSemanticdb.revision
ThisBuild / scalafixDependencies += "com.github.liancheng" %% "organize-imports" % "0.6.0"

lazy val compilerOptions = Seq(
  "-encoding",
  "utf-8",
  "-target:jvm-1.8",
  "-deprecation",
  "-feature",
  "-unchecked",
  "-Xfatal-warnings",
  "-Ykind-projector"
)

lazy val dependencies = {
  val versions = new {
		$if(cats_effect.truthy)$
    val catsEffect    = "$cats_effect_version$"
    val stCatsEffect  = "1.3.0"
		$endif$
		$if(fs2.truthy)$
    val fs2           = "$fs2_version$"
		$endif$
    val scalatest     = "$scalatest_version$" 
  }

  Seq(
    $if(cats_effect.truthy)$
    "org.typelevel"     %% "cats-effect"                   % versions.catsEffect,
    "org.typelevel"     %% "cats-effect-testing-scalatest" % versions.stCatsEffect % "test",
    $endif$
    $if(fs2.truthy)$
    "co.fs2"            %% "fs2-core"                      % versions.fs2,
    $endif$
    "org.scalatest"     %% "scalatest"                     % versions.scalatest    % "test",
  )
}


lazy val `$name$` = (project in file("."))
  .settings(
    scalacOptions ++= compilerOptions,
    libraryDependencies ++= dependencies
)
