package pl.spark.quickstart

import pl.spark.SparkSetup

object QuickStartApp extends SparkSetup {

  def main(args: Array[String]): Unit = {
    val (spark, textFile) = initSpark(args)
    import spark.implicits._

    // do some operations on data frame
    println(s"Number of items in Dataset: ${textFile.count()}")
    println(s"First line: ${textFile.first()}")

    val linesWithSpark = textFile.filter(line => line.contains("Spark"))
    println(s"Lines with 'Spark' phrase: ${linesWithSpark.count()}")

    // transform dataset
    val lineMostWords = textFile.map(line => line.split(" ").length).reduce((a, b) => Math.max(a, b))
    println(lineMostWords)

    // MapReduce in spark and print only if word occurrence is greater than 1
    val results = textFile.flatMap(line => line.toLowerCase().split(" ")).groupByKey(identity).count().collect()
    results.filter(result => result._2 > 1).sortBy(- _._2)
      .foreach(result => println(s"Phrase '${result._1}' occurs ${result._2} times"))

    spark.stop()
  }
}
