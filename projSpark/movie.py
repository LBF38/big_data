#!/usr/bin/python
# -*- coding: utf-8 -*-

# an instance consists in a line of the file
class Movie(object):
    # constructor
    def __init__(self, line):
        # decompose a line in fields
        self.fields = line.split(";")
    # display
    def __str__(self):
        return "Movie[%s]" % ", ".join(self.fields)
    # title of the movie or None if on the first line
    def title(self):
        if self.fields[1] == u"title":
            return None
        return self.fields[1]
    # example of float or None if null or incorrect
    def attribute(self):
        try:
            return float(self.fields[6])
        except:
            return None
