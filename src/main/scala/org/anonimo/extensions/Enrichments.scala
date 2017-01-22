package org.anonimo.extensions

import org.anonimo.core.{Destination, Source}
import org.apache.spark.sql.{DataFrame, DataFrameReader, DataFrameWriter, Row}

object Enrichments {

  implicit class EnrichedDataFrameReader(dataFrameReader: DataFrameReader) {
    def fromSource(source: Source): DataFrame= {
      source.readDataFrame(dataFrameReader)
    }
  }

  implicit class EnrichedDataFrame(dataFrameWriter: DataFrameWriter[Row]) {
    def toDestination(destination: Destination) = {
      destination.writeDataFrame(dataFrameWriter)
    }
  }

}