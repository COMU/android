#!/usr/bin/env python
#-*- coding: UTF-8 -*-

from django.conf.urls.default import *
from piston.resource import Resource
from resttest.myapp.api.handlers import MeslekHandler

meslek_handler = Resource(MeslekHandler)

urlpatterns = patterns('',
    url(r'^meslek/(?P<meslek_adi>[^/]+)/', meslek_handler),
    url(r'^meslekler/', meslek_handler),
)
