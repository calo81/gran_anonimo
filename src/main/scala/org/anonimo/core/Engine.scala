package org.anonimo.core

import org.anonimo.extensions.Enrichments._

case class Engine(configurator: Configurator) {

  implicit val sparkSession = configurator.sparkSession
  val source = configurator.source
  val destination = configurator.destination

  def run = {
    sparkSession
      .read
      .fromSource(source)
      .transform(configurator.anonymizer)
      .write
      .toDestination(destination)
  }
}
