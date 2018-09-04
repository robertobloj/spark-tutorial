package pl.spark

import scala.collection.mutable.ArrayBuffer

object CacheExampleApp extends SparkApp {

  private def average(list: ArrayBuffer[Long]): Double = list.foldLeft(0.0)(_+_) / list.foldLeft(0.0)((r,_) => r+1)
  private val linesWithSpark = textFile.filter(line => line.contains("Spark"))
  private val iterations = 50

  // first time is always the longest, we don't want to measure it
  linesWithSpark.count()

  // testing no cache
  val timesNoCache = ArrayBuffer.empty[Long]
  for (_ <- 1 to iterations) {
    val start = System.currentTimeMillis()
    linesWithSpark.count()
    val end = System.currentTimeMillis()
    timesNoCache += (end-start)
  }

  linesWithSpark.cache()
  linesWithSpark.count()

  // testing cache
  val timesCache = ArrayBuffer.empty[Long]
  for (_ <- 1 to iterations) {
    val start = System.currentTimeMillis()
    linesWithSpark.count()
    val end = System.currentTimeMillis()
    timesCache += (end-start)
  }

  // stats
  println(s"Times NO_CACHE: avg=${average(timesNoCache)}, values: $timesNoCache")
  println(s"Times    CACHE: avg=${average(timesCache)}, values: $timesCache")

  spark.stop()

}
