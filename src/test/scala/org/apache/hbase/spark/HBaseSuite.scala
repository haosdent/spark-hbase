/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.hbase.spark

import org.apache.spark.sql.test._
import org.scalatest.{BeforeAndAfterAll, FunSuite}
import org.apache.hadoop.hbase.{HConstants, HBaseTestingUtility}

/* Implicits */
import TestSQLContext._

class HBaseSuite 
  extends FunSuite
  with BeforeAndAfterAll {

  val util = new HBaseTestingUtility()

  override def beforeAll() {
    util.startMiniCluster()
  }

  override def afterAll() {
    util.shutdownMiniCluster()
  }

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