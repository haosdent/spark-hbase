name := "spark-hbase"

version := "0.1"

organization := "org.apache"

scalaVersion := "2.10.4"

libraryDependencies += "org.apache.spark" %% "spark-sql" % "1.2.0" % "provided"

libraryDependencies += "org.apache.hbase" % "hbase" % "0.99.2"

libraryDependencies += "org.apache.hbase" % "hbase-common" % "0.99.2"

libraryDependencies += "org.apache.hbase" % "hbase-server" % "0.99.2"

libraryDependencies += "org.apache.hbase" % "hbase-client" % "0.99.2"

libraryDependencies += "org.apache.avro" % "avro" % "1.7.7" exclude("org.mortbay.jetty", "servlet-api")

libraryDependencies += "org.apache.avro" % "avro-mapred" % "1.7.7" exclude("org.mortbay.jetty", "servlet-api")

publishMavenStyle := true

publishTo := {
  val nexus = "https://oss.sonatype.org/"
  if (version.value.endsWith("SNAPSHOT"))
    Some("snapshots" at nexus + "content/repositories/snapshots")
  else
    Some("releases"  at nexus + "service/local/staging/deploy/maven2")
}

pomExtra := (
  <url>https://github.com/spark-hbase/spark-hbase</url>
  <licenses>
    <license>
      <name>Apache License, Verision 2.0</name>
      <url>http://www.apache.org/licenses/LICENSE-2.0.html</url>
      <distribution>repo</distribution>
    </license>
  </licenses>
  <scm>
    <url>git@github.com:spark-hbase/spark-hbase.git</url>
    <connection>scm:git:git@github.com:spark-hbase/spark-hbase.git</connection>
  </scm>
  <developers>
    <developer>
      <id>haosdent</id>
      <name>Haosong Huang</name>
      <url>https://github.com/haosdent</url>
    </developer>
  </developers>)

// Enable Junit testing.
// libraryDependencies += "com.novocode" % "junit-interface" % "0.9" % "test"

libraryDependencies += "org.scalatest" %% "scalatest" % "2.2.1" % "test"
