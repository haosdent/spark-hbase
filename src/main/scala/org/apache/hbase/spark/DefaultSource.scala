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

import org.apache.spark.sql.SQLContext
import org.apache.spark.sql.sources.RelationProvider

/**
 * Provides access to HBase data from pure SQL statements (i.e. for users of the
 * JDBC server).
 */
class DefaultSource extends RelationProvider {

  /**
   * Creates a new relation for data store in hbase given a `hbase-site` configuration file location as a parameter.
   */
  def createRelation(sqlContext: SQLContext, parameters: Map[String, String]) = {
    val tableName = parameters("table")
    val schemaDefine = parameters("schema")
    val hbaseSite = parameters("hbase-site")
    HBaseRelation(tableName, schemaDefine, hbaseSite)(sqlContext)
  }
}

