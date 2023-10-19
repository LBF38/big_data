#!/usr/bin/python
# -*- coding: utf-8 -*-
# http://spark.apache.org/docs/latest/api/python/pyspark.html#pyspark.RDD
# execution with spark-submit count.py
from pyspark import SparkConf, SparkContext
# Execution context for spark-submit
appName = "count"
conf = SparkConf().setAppName(appName)
sc = SparkContext(conf=conf)
## Count
# open the file
# raw = sc.textFile("hdfs://localhost:9001/user/movies.csv")
raw = sc.textFile("movies.csv")
# Number of lines
nb = raw.count()
print("Number of lines:", nb)
