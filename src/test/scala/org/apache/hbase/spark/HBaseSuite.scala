package org.apache.hbase.spark

import org.apache.spark.sql.test._
import org.scalatest.FunSuite

/* Implicits */
import TestSQLContext._

class HBaseSuite extends FunSuite {

  test("dsl test") {
    val tableName = "spark_read_test"
    val schemaDefine = "rowkey string, cf:qual1 int, cf:qual2 double"
    val hbaseSite = "/tmp/hbase-site.xml"
    
    val results = TestSQLContext
      .hbaseTable(tableName, schemaDefine, hbaseSite)
      .select('title)
      .collect()

    assert(results.size === 8)
  }

  test("sql test") {
    val tableName = "spark_read_test"
    val schemaDefine = "rowkey string, cf:qual1 int, cf:qual2 double"
    val hbaseSite = "/tmp/hbase-site.xml"

    sql(
      s"""
        |CREATE TEMPORARY TABLE hbaseTable
        |USING org.apache.hbase.spark
        |OPTIONS (
        |  table "$tableName",
        |  schema "$schemaDefine",
        |  hbase-site "$hbaseSite",
        |)
      """.stripMargin.replaceAll("\n", " "))

    assert(sql("SELECT * FROM hbaseTable").collect().size === 8)
  }
}