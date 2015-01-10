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

import java.io.IOException

import org.apache.hadoop.hbase.util.Bytes
import org.apache.spark.sql.catalyst.types.{DataType, _}

/**
 * A representation of HBase Column
 * @param family HBase Column Family
 * @param qualifier HBase Column Qualifier
 * @param dataType type [[org.apache.spark.sql.catalyst.types.DataType]]
 */
class HBaseColumn(val family: Array[Byte], val qualifier: Array[Byte], val dataType: DataType, val isRowkey: Boolean)
  extends Serializable {

  var value: Array[Byte] = null

  def toSqlVal() = {
    val convertedVal = dataType match {
      case BooleanType => Bytes.toBoolean(value)
      case ShortType => Bytes.toShort(value)
      case IntegerType => Bytes.toInt(value)
      case FloatType => Bytes.toFloat(value)
      case DoubleType => Bytes.toDouble(value)
      case LongType => Bytes.toLong(value)
      case StringType => Bytes.toString(value)
      case _ => throw new IOException("Unsupported data type")
    }
    convertedVal
  }

  def toStructField() = {
    StructField(Bytes.toString(family) + Bytes.toString(qualifier), dataType, nullable = false)
  }

  def setVal(value_ : Array[Byte]) = {
    value = value_
    this
  }
}

object HBaseColumn {

  def apply(familyStr: String, qualifierStr: String, dataTypeStr: String) = {
    val dataType = dataTypeStr.toLowerCase match {
      case "string" => BooleanType
      case _ => throw new IOException("Unsupported data type")
    }
    (familyStr, qualifierStr) match {
      case (null, null) => new HBaseColumn(null, null, dataType, false)
      case _ => new HBaseColumn(Bytes.toBytes(familyStr), Bytes.toBytes(qualifierStr), dataType, false)
    }
  }
}