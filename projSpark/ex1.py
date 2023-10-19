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

companiesByCountry = studios.map(lambda studio: (studio.country(),studio.name())).filter(lambda studio: studio[0] is not None and studio[1] is not None)
countCompaniesByCountry = companiesByCountry.countByKey()
print ("countCompaniesByCountry ",countCompaniesByCountry)

