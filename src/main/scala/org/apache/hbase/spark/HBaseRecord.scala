package org.apache.hbase.spark

import org.apache.hadoop.hbase.client.Result

class HBaseRecord(val schema: HBaseSchema, val rs: Result) {

  def values() = {
    schema.columns.map {
      column => (column.family, column.qualifier) match {
        case (null, null) => column.setVal(rs.getRow).toSqlVal()
        case _ => column.setVal(rs.getValue(column.family, column.qualifier)).toSqlVal()
      }
    }
  }
}

object HBaseRecord {

  def apply(schema: HBaseSchema, rs: Result) = {
    new HBaseRecord(schema, rs)
  }
}
