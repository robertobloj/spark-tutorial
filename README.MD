# Apache Spark Tutorial

Project contains some examples how spark works.
Most of examples are copied from official [website](https://spark.apache.org/).

Examples are a little bit customized.


## Examples

1. Quick start

Run following command:

```
cd spark-tutorial

[SPARK_HOME]/bin/spark-submit \
    --class "pl.spark.QuickStartApp"  \
    --master local[4] \
    target/scala-2.11/spark-tutorial_2.11-0.1.jar \
    [FILE_NAME]
```

where:

- *SPARK_HOME* - home directory for spark
- *FILE_NAME* - file to analyse

2. Caching example

Run following command:

```
[SPARK_HOME]/bin/spark-submit \
    --class "pl.spark.CacheExampleApp"  \
    --master local[4] \
    target/scala-2.11/spark-tutorial_2.11-0.1.jar \
    [FILE_NAME]
```

Example result:

```
    Times NO_CACHE: avg=242.54, values: ArrayBuffer(375, 328, 328, 309, 266, ...)
    Times    CACHE: avg=86.56, values: ArrayBuffer(93, 94, 125, 78, 78, ...)
```
