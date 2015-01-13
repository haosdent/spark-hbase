name := "spark-hbase"

version := "0.1"

organization := "org.apache"

scalaVersion := "2.10.4"

libraryDependencies += "org.apache.spark" %% "spark-sql" % "1.2.0" % "provided" exclude("javax.servlet", "servlet-api")

libraryDependencies += "org.apache.hbase" % "hbase" % "0.98.9-hadoop2" exclude("javax.servlet", "servlet-api")

libraryDependencies += "org.apache.hbase" % "hbase-common" % "0.98.9-hadoop2" exclude("javax.servlet", "servlet-api")

libraryDependencies += "org.apache.hbase" % "hbase-server" % "0.98.9-hadoop2" exclude("org.mortbay.jetty", "servlet-api-2.5")

libraryDependencies += "org.apache.hbase" % "hbase-client" % "0.98.9-hadoop2" exclude("javax.servlet", "servlet-api")

libraryDependencies += "org.apache.hbase" % "hbase-hadoop-compat" % "0.98.9-hadoop2" exclude("javax.servlet", "servlet-api")

libraryDependencies += "org.apache.hbase" % "hbase-hadoop2-compat" % "0.98.9-hadoop2" exclude("javax.servlet", "servlet-api")

libraryDependencies += "org.apache.hadoop" % "hadoop-common" % "2.2.0" exclude("javax.servlet", "servlet-api")

libraryDependencies += "org.apache.hadoop" % "hadoop-hdfs" % "2.2.0" exclude("javax.servlet", "servlet-api")

libraryDependencies += "org.apache.hadoop" % "hadoop-client" % "2.2.0" exclude("javax.servlet", "servlet-api")

libraryDependencies += "org.apache.hadoop" % "hadoop-minicluster" % "2.2.0" exclude("javax.servlet", "servlet-api")

libraryDependencies += "org.apache.hbase" % "hbase-common" % "0.98.9-hadoop2" classifier "tests" exclude("javax.servlet", "servlet-api")

libraryDependencies += "org.apache.hbase" % "hbase-server" % "0.98.9-hadoop2" classifier "tests" exclude("javax.servlet", "servlet-api")

libraryDependencies += "org.apache.hbase" % "hbase-hadoop-compat" % "0.98.9-hadoop2" classifier "tests" exclude("javax.servlet", "servlet-api")

libraryDependencies += "org.apache.hbase" % "hbase-hadoop2-compat" % "0.98.9-hadoop2" classifier "tests" exclude("javax.servlet", "servlet-api")

libraryDependencies += "org.apache.hadoop" % "hadoop-common" % "2.2.0" classifier "tests" exclude("javax.servlet", "servlet-api")

libraryDependencies += "org.apache.hadoop" % "hadoop-hdfs" % "2.2.0" classifier "tests" exclude("javax.servlet", "servlet-api")

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