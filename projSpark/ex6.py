# -*- coding: utf-8 -*-
# execution spark-submit --py-files game.py titles.py
from pyspark import SparkConf, SparkContext

# execution context for spark-submit
appName = "movies"
conf = SparkConf().setAppName(appName) #.setMaster("spark://debian:7077")
sc = SparkContext(conf=conf)

# classe Movie
from movie import Movie

# open the file
raw = sc.textFile("movies.csv")

# lines as movies
movies = raw.map(lambda ligne: Movie(ligne))


# Year w/ max movies
moviesPerYear = movies.filter(lambda movie: movie.release() is not None).map(lambda movie: (movie.release().year, 1)).reduceByKey(lambda a, b: a + b)
print("Year w/ max movies: ", moviesPerYear.max())

