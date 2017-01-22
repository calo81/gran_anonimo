package org.anonimo.core

import java.io.File

import com.typesafe.config.{Config, ConfigFactory}
import org.apache.spark.SparkConf
import org.apache.spark.sql.{Dataset, Row, SparkSession}
import scala.collection.JavaConversions._

case class Configurator(configFile: File) {

  val conf = Configuration(ConfigFactory.parseFile(configFile))
  val sparkConf = configureSparkConf

  lazy val anonymizer: (Dataset[Row]) => Dataset[Row] = {input => input}
  lazy val sparkSession = SparkSession.builder().config(sparkConf).master("local").appName("hide my fields").getOrCreate()

  def source(implicit sparkSession: SparkSession) =  {
    val inConfig = conf.config.getConfig("in")
    val source = inConfig.getString("class")
    val sourceClass = Class.forName(source).asInstanceOf[Class[Source]]
    sourceClass.getConstructor(classOf[Configuration]).newInstance(conf)
  }

  def destination(implicit sparkSession: SparkSession) = {
    val outConfig = conf.config.getConfig("out")
    val destination = outConfig.getString("class")
    val destinationClass = Class.forName(destination).asInstanceOf[Class[Destination]]
    destinationClass.getConstructor(classOf[Configuration]).newInstance(conf)
  }

  private def configureSparkConf = {
    val sparkConf = new SparkConf()
    conf.config.getStringList("spark-confs").map(_.split("=")).foreach(a => sparkConf.set(a(0), a(1)))
    sparkConf
  }

}


case class Configuration(config: Config) {

}