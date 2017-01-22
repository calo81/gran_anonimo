lazy val root = (project in file(".")).
  settings(
    name := "anonimo",
    version := "0.1",
    scalaVersion := "2.11.8"
  )

val sparkVersion = "2.1.0"

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % sparkVersion,
  "org.apache.spark" %% "spark-sql" % sparkVersion,
  "org.mongodb.spark" %% "mongo-spark-connector" % "2.0.0",
  "com.github.scopt" %% "scopt" % "3.5.0",
  "com.typesafe" % "config" % "1.3.1"
)
