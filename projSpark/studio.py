#!/usr/bin/python
# -*- coding: utf-8 -*-

# an instance consists in a line of the file
class Studio(object):
    # constructor
    def __init__(self, line):
        # decompose a line in fields
        self.fields = line.split(";")

    # display
    def __str__(self):
        return "Studio[%s]" % ", ".join(self.fields)

    def id(self):
        try:
            return int(self.fields[0])
        except:
            return None
    def name(self):
        if self.fields[1] == "name":
            return None
        return self.fields[1]

    def country(self):
        if self.fields[2] == "country":
            return None
        return self.fields[2]

    def logo(self):
        if self.fields[3] == "logo":
            return None
        return self.fields[3]
