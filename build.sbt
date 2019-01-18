organization in ThisBuild := "com.knoldus"
version in ThisBuild := "1.0-SNAPSHOT"


scalaVersion in ThisBuild := "2.12.4"

lazy val `crud` = (project in file("."))
  .aggregate(`product-api`, `product-impl`)
lazy val `product-api` = (project in file("product-api"))
  .settings(
    libraryDependencies ++= Seq(
      lagomScaladslApi
    )
  )
lazy val `product-impl` = (project in file("product-impl"))
  .enablePlugins(LagomScala)
  .settings(
    libraryDependencies ++= Seq(
      lagomScaladslPersistenceCassandra,
      lagomScaladslKafkaBroker,
      lagomScaladslTestKit,
      macwire,
      scalaTest
    )
  )
  .settings(lagomForkedTestSettings: _*)
  .dependsOn(`product-api`)
val macwire = "com.softwaremill.macwire" %% "macros" % "2.3.0" % "provided"
val scalaTest = "org.scalatest" %% "scalatest" % "3.0.4" % Test


lagomUnmanagedServices in ThisBuild := Map("external-service" -> "https://jsonplaceholder.typicode.com")
