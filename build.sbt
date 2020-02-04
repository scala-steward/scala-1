lazy val commonSettings = Settings.value ++ Seq(
  organization := "io.github.mvillafuertem",
  version := "0.1",
  scalaVersion := "2.13.1",
  homepage := Some(url("https://github.com/mvillafuertem/scala")),
  licenses := List("MIT" -> url("http://www.apache.org/licenses/LICENSE-2.0")),
  developers := List(
    Developer(
      "mvillafuertem",
      "Miguel Villafuerte",
      "mvillafuertem@email.com",
      url("https://github.com/mvillafuertem")
    )
  )
)

lazy val scala = (project in file("."))
  .aggregate(
    advanced,
    `akka-untyped`,
    `akka-typed`,
    alpakka,
    algorithms,
    basic,
    cats,
    json,
    slick,
    todo,
    products,
    `sensor-controller`,
    zio
  )
  // S E T T I N G S
  .settings(commonSettings)
  .settings(Settings.noPublish)

lazy val advanced = (project in file("advanced"))
  // S E T T I N G S
  .settings(commonSettings)
  .settings(libraryDependencies ++= Dependencies.advanced)

lazy val `akka-untyped` = (project in file("akka-untyped"))
  .configs(IntegrationTest)
  .settings(Defaults.itSettings)
  // S E T T I N G S
  .settings(commonSettings)
  .settings(NexusSettings.value)
  .settings(libraryDependencies ++= Dependencies.`akka-untyped`)

lazy val `akka-typed` = (project in file("akka-typed"))
  .configs(IntegrationTest)
  .settings(Defaults.itSettings)
  // S E T T I N G S
  .settings(commonSettings)
  .settings(NexusSettings.value)
  .settings(libraryDependencies ++= Dependencies.`akka-typed`)

lazy val alpakka = (project in file("alpakka"))
  .configs(IntegrationTest)
  .settings(Defaults.itSettings)
  // S E T T I N G S
  .settings(commonSettings)
  .settings(libraryDependencies ++= Dependencies.alpakka)

lazy val basic = (project in file("basic"))
  // S E T T I N G S
  .settings(commonSettings)
  .settings(libraryDependencies ++= Dependencies.basic)

lazy val todo = (project in file("applications/todo"))
  // S E T T I N G S
  .settings(commonSettings)
  .settings(BuildInfoSettings.value)
  .settings(libraryDependencies ++= Dependencies.todo)
  // P L U G I N S
  .enablePlugins(BuildInfoPlugin)
  .enablePlugins(GitVersioning)

lazy val products = (project in file("applications/products"))
  // S E T T I N G S
  .settings(commonSettings)
  .settings(BuildInfoSettings.value)
  .settings(libraryDependencies ++= Dependencies.products)
  // P L U G I N S
  .enablePlugins(BuildInfoPlugin)
  .enablePlugins(GitVersioning)

lazy val `sensor-controller` = (project in file("applications/sensor-controller"))
  // S E T T I N G S
  .settings(commonSettings)
  .settings(libraryDependencies ++= Dependencies.`sensor-controller`)
  .settings(
    PB.targets in Compile := Seq(
      scalapb.gen() -> (sourceManaged in Compile).value
    ))
  // P L U G I N S
  .enablePlugins(GitVersioning)

lazy val algorithms = (project in file("algorithms"))
  // S E T T I N G S
  .settings(commonSettings)
  .settings(libraryDependencies ++= Dependencies.algorithms)

lazy val cats = (project in file("cats"))
  .dependsOn(algorithms)
  // S E T T I N G S
  .settings(commonSettings)
  .settings(libraryDependencies ++= Dependencies.cats)

lazy val json = (project in file("json"))
  .dependsOn(algorithms)
  // S E T T I N G S
  .settings(commonSettings)
  .settings(libraryDependencies ++= Dependencies.json)

lazy val slick = (project in file("slick"))
  // S E T T I N G S
  .settings(commonSettings)
  .settings(libraryDependencies ++= Dependencies.slick)

lazy val zio = (project in file("zio"))
  // S E T T I N G S
  .settings(commonSettings)
  .settings(libraryDependencies ++= Dependencies.zio)
  .settings(testFrameworks := Seq(new TestFramework("zio.test.sbt.ZTestFramework")))