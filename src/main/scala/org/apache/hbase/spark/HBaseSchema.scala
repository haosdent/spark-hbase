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

import org.apache.spark.sql.catalyst.types.StructType

class HBaseSchema(val columns: Array[HBaseColumn]) {

  def toStructType() = {
    val types = columns.map {
      column => column.toStructField()
    }
    StructType(types)
  }
}

object HBaseSchema {

  def parseFrom(define: String) = {
    val columns = define.split(',').map(_.trim).map {
      snippet => snippet.split(' ').map(_.trim) match {
        case Array(column: String, typ: String) =>
          column.split(':').map(_.trim) match {
            case Array(family: String, qualifier: String) =>
              HBaseColumn(family, qualifier, typ)
            case _ => HBaseColumn(null, null, typ)
          }
      }
    }
    new HBaseSchema(columns)
  }
}
