#!/usr/bin/python
# -*- coding: utf-8 -*-

# an instance consists in a line of the file
import datetime


class Movie(object):
    # constructor
    def __init__(self, line):
        # decompose a line in fields
        self.fields = line.split(";")

    # display
    def __str__(self):
        return "Movie[%s]" % ", ".join(self.fields)

    def id(self):
        if self.fields[0] == u"idimdb":
            return None
        return self.fields[0]

    def title(self):
        if self.fields[1] == u"title":
            return None
        return self.fields[1]


    def release(self):
        if self.fields[2] == u"release":
            return None
        return datetime.date.fromisoformat(self.fields[2]) # Date

    def runtime(self):
        if self.fields[3] == u"runtime":
            return None
        try:
            return int(self.fields[3])
        except:
            return None

    def budget(self):
        if self.fields[4] == u"budget":
            return None
        try:
            return int(self.fields[4])
        except:
            return None

    def revenue(self):
        if self.fields[5] == u"revenue":
            return None
        try:
            return float(self.fields[5])
        except:
            return None

    def poster(self):
        if self.fields[6] == u"poster":
            return None
        return self.fields[6]

    def rating(self):
        try:
            return float(self.fields[7])
        except:
            return None
