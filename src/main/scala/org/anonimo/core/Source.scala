package org.anonimo.core

import org.apache.spark.sql.{DataFrame, DataFrameReader, SparkSession}

trait Source {
  val configuration: Configuration
  def readDataFrame(dataFrameReader: DataFrameReader): DataFrame
}
