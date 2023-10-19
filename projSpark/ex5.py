# -*- coding: utf-8 -*-
# execution spark-submit --py-files game.py titles.py
from pyspark import SparkConf, SparkContext
from studio import Studio

# execution context for spark-submit
appName = "titles"
conf = SparkConf().setAppName(appName) #.setMaster("spark://debian:7077")
sc = SparkContext(conf=conf)

# open the file
raw = sc.textFile("studios.csv")
studios = raw.map(lambda ligne: Studio(ligne))

orderedStudios = studios.filter(lambda studio: studio.name() is not None).sortBy(lambda studio: studio.name(), ascending=False)
print("Last studio by alphabetical order: ", orderedStudios.first().name())
