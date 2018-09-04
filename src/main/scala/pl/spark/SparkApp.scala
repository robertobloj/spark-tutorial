package pl.spark

import org.apache.spark.sql.SparkSession

class SparkApp extends App {

  // setup
  if (args.length == 0) {
    System.err.println("Dude, I need filename path as input parameter")
    System.exit(-1)
  }
  val filename = args(0)

  val spark = SparkSession.builder()
    .appName("test")
    .master("local")
    .getOrCreate()
  val textFile = spark.read.textFile(filename)
}
