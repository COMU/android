#!/usr/bin/python
# -*- coding: utf-8 -*-

from piston.handler import BaseHandler
from piston.utils import rc,throttle
from myapp.models import *
from api.decodebase64 import *
from api.hash import *
class KonumHandler(BaseHandler):
    allowed_methods = ("GET", "POST", "PUT")

    def read(self,request):
        decoder = DecodeBase64()
        liste = []
        
        if request.GET.has_key("param"):
            liste = decoder.returnParams(request.GET.get('param'))
            print liste
        try:
            kullanici = Kullanici.objects.get(email = liste[0], parola = liste[1],
                    dogrulama_id = liste[2],durum = True)
            return kullanici.konum
        except Kullanici.DoesNotExist:
            return -1

    def uptade(self,request,konum_id):
        decoder = DecodeBase64()
        liste = decoder.returnParams(request.PUT.get('params'))
        print liste
        kullanici = Kullanici.objects.get(dogrulama_id = liste[0], email = liste[1],
                parola = liste[2])
        konum=Kullanici.konum
        konum.enlem = liste[3]
        konum.boylam = liste[4]
        konum.sehir.adi = liste[5]
        konum.ulke.adi = liste[6]
        return rc.ALL_OK
    def create(self,request):
        konum=Konum.objects.create(enlem=request.POST.get("enlem"),
                boylam=request.POST.get("boylam"), 
                sehir=Sehir.objects.create(adi=request.POST.get("sehir_adi")),
                ulke=Ulke.objects.create(adi=request.POST.get("ulke_adi")) )
        return rc.CREATED
