package org.apache.hbase.spark

import org.apache.hadoop.hbase.HBaseConfiguration
import org.apache.hadoop.hbase.mapreduce.TableInputFormat
import org.apache.spark.sql._
import org.apache.spark.sql.sources.TableScan

case class HBaseRelation(tableName: String, schemaDefine: String, hbaseSite: String)(@transient val sqlContext: SQLContext) extends TableScan {

  val hbaseSchema = HBaseSchema.parseFrom(schemaDefine)

  val schema = {
    hbaseSchema.toStructType()
  }

  lazy val buildScan = {
    val conf = HBaseConfiguration.create()
    conf.set(TableInputFormat.INPUT_TABLE, tableName)
    val hbaseRDD = sqlContext.sparkContext.newAPIHadoopRDD(conf, classOf[TableInputFormat],
      classOf[org.apache.hadoop.hbase.io.ImmutableBytesWritable],
      classOf[org.apache.hadoop.hbase.client.Result])
    hbaseRDD.map { record =>
      val values = HBaseRecord(hbaseSchema, record._2).values()
      Row.fromSeq(values)
    }
  }
}

