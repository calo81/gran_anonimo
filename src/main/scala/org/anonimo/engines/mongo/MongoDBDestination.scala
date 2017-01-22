package org.anonimo.engines.mongo

import com.mongodb.spark.sql._
import org.anonimo.core.{Configuration, Destination}
import org.apache.spark.sql.{DataFrameWriter, Row}

class MongoDBDestination(val configuration: Configuration) extends Destination {
  override def writeDataFrame(dataFrameWriter: DataFrameWriter[Row]): Unit = {
    dataFrameWriter.mongo()
  }
}
