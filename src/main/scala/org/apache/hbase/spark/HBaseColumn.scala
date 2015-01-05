package org.apache.hbase.spark

import org.apache.spark.sql.catalyst.types.DataType

/**
 * A representation of HBase Column
 * @param family HBase Column Family
 * @param qualifier HBase Column Qualifier
 * @param dataType type [[org.apache.spark.sql.catalyst.types.DataType]]
 */
class HBaseColumn(val family: Array[Byte], val qualifier: Array[Byte], val dataType: DataType)
  extends Serializable

