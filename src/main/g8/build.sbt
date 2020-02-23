
lazy val baseSettings: Seq[Setting[_]] = Seq(
  scalaVersion       := "$scala_version$",
  scalacOptions     ++= Seq(
    "-deprecation",
    "-encoding",
    "UTF-8",
    "-feature",
    "-language:higherKinds",
    "-language:implicitConversions",
    "-language:existentials",
    "-language:postfixOps",
    "-unchecked",
    "-Ywarn-value-discard"
  ),
  resolvers += Resolver.sonatypeRepo("releases")
)

lazy val $name$ = project.in(file("."))
  .settings(moduleName := "$name$")
  .settings(baseSettings: _*)
  .aggregate(core, slides)
  .dependsOn(core, slides)

lazy val core = project
  .settings(moduleName := "$name$-core")
  .settings(baseSettings: _*)


lazy val slides = project
  .dependsOn(core)
  .settings(moduleName := "$name$-slides")
  .settings(baseSettings: _*)
  .settings(
    mdocIn := baseDirectory.value / "mdoc",
    mdocOut := baseDirectory.value / "docs",
  )
  .enablePlugins(MdocPlugin)