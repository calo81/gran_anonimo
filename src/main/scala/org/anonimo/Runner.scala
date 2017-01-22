package org.anonimo

import java.io.File

import org.anonimo.core.{Configurator, Engine}

object Runner {

  case class Config(file: String = "")

  def main(args: Array[String]) = {
    val parser = new scopt.OptionParser[Config]("anonimo") {
      head("anonimo", "0.1")
      opt[String]('c', "config").action((x, c) =>
        c.copy(file = x))
    }

    parser.parse(args, Config()) match {
      case Some(config) if new File(config.file).exists() =>
        configureAndRun(config)
      case None => throw new IllegalStateException("Incorrect invocation of Runner")
      case _ =>  throw new IllegalStateException(s"Incorrect invocation of Runner with ${args.mkString(",")}")
    }

  }

  private def configureAndRun(config: Config) = {
    val configurator = Configurator(new File(config.file))
    Engine(configurator).run
  }
}