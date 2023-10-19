# -*- coding: utf-8 -*-
# execution spark-submit --py-files game.py titles.py
from pyspark import SparkConf, SparkContext

# execution context for spark-submit
appName = "titles"
conf = SparkConf().setAppName(appName) #.setMaster("spark://debian:7077")
sc = SparkContext(conf=conf)

# classe Movie
from movie import Movie

# open the file
raw = sc.textFile("movies.csv")

# lines as movies
movies = raw.map(lambda ligne: Movie(ligne))

# filtering title (None if unknown)
titles = movies.map(lambda movie: movie.title())

# removing None values from the RDD
titles_ok = titles.filter(lambda title : title is not None)
print ("titles ",titles_ok.collect())
