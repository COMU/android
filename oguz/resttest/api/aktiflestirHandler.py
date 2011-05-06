#!/usr/bin/python
# -*- coding: utf-8 -*-

from piston.handler import BaseHandler
from piston.utils import rc,throttle
from myapp.models import *
from api.decodebase64 import *
import base64
class Aktiflestir(BaseHandler):
    allowed_methods = ("GET")
    def read(self,request):
        param = ""
        if request.GET.has_key('param'):
            decoder = DecodeBase64()
            params = request.GET.get('param')
            liste = decoder.returnParams(params)
            print liste
            try:
                kullanici = Kullanici.objects.get(email = liste[0])
            except Kullanici.DoesNotExist:
                return rc.NOT_FOUND
            if kullanici.dogrulama_id == liste[1]:
                kullanici.durum = True
                kullanici.save()
                return rc.ALL_OK
        return rc.NOT_FOUND
