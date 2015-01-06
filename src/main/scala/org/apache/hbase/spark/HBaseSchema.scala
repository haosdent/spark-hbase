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
