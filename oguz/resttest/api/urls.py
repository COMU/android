#!/usr/bin/env python
#-*- coding: UTF-8 -*-

from django.conf.urls.defaults import *
from piston.resource import Resource
from handler import MeslekHandler

meslek_handler = Resource(MeslekHandler)

urlpatterns = patterns('',
    url(r'^meslek/(?P<meslek_id>[^/]+)', meslek_handler),
    url(r'^meslekler/$', meslek_handler)
)
