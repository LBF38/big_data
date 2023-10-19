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

companiesByCountry = studios.map(lambda studio: (studio.country(),studio.name())).filter(lambda studio: studio[0] is not None and studio[1] is not None).groupByKey()
firstStudioByCountry = companiesByCountry.map(lambda studio: (studio[0],list(studio[1])[0]))
print("first studio per country", firstStudioByCountry.collect())
