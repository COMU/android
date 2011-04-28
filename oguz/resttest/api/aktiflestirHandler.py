#!/usr/bin/python
# -*- coding: utf-8 -*-

from piston.handler import BaseHandler
from piston.utils import rc,throttle
from myapp.models import *

class Aktiflestir(BaseHandler):
    allowed_methods = ("GET")
    def read(self,request,dogrulama):
        kullanici = Kullanici.objects.get(dogrulama_id = dogrulama)
        kullanici.durum = True
        kullanici.save()
        return rc.ALL_OK
