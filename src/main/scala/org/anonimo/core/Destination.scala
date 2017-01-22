package org.anonimo.core

import org.apache.spark.sql._

trait Destination {
  val configuration: Configuration
  def writeDataFrame(dataFrameWriter: DataFrameWriter[Row])
}
