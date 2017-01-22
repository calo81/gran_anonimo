package org.anonimo.engines.mongo

import com.mongodb.spark.sql._
import org.anonimo.core.{Configuration, Source}
import org.apache.spark.sql.{DataFrame, DataFrameReader}


class MongoDBSource(val configuration: Configuration) extends Source {
  override def readDataFrame(dataFrameReader: DataFrameReader): DataFrame = {
    dataFrameReader.mongo()
  }
}
