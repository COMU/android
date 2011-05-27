#!/usr/bin/python
# -*- coding: utf-8 -*-
from api.hash import Crypt
from api.decodebase64 import *
from piston.handler import BaseHandler
from piston.utils import rc,throttle
from myapp.models import *
import base64
class AramaHandler(BaseHandler):
    allowed_methods=("GET")
    def read(self,request):
        decoder = DecodeBase64()
        liste=[]

        if request.GET.has_key('param'):
            liste = decoder.returnParams(request.GET.get('param'))
            try:
                kullanici=Kullanici.objects.get(email=liste[0],
                        parola=liste[1],
                        dogrulama_id=liste[2],
                        durum=True)
                return Kullanici.objects.filter(konum = Konum.objects.filter(sehir = Sehir.objects.filter(adi = liste[4]), ulke = Ulke.objects.filter(adi = liste[3])))
            except:
                return -1
