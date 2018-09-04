package pl.spark

import org.apache.spark.sql.{Dataset, SparkSession}

class SparkSetup {

  def initSpark(args: Array[String]) : (SparkSession, Dataset[String]) = {
    if (args.length == 0) {
      System.err.println("Dude, I need filename path as input parameter")
      System.exit(-1)
    }

    val spark = SparkSession.builder()
      .appName("test")
      .master("local")
      .getOrCreate()

    (spark, spark.read.textFile(args(0)))
  }
}
