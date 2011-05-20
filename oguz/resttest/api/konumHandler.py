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

    def update(self,request):
        decoder = DecodeBase64()
        print "konum update"
        liste = decoder.returnParams(request.PUT.get('params'))
        print liste
        
        kullanici = Kullanici.objects.get(dogrulama_id = liste[0], email = liste[1],
                parola = liste[2])
        konum=kullanici.konum
        konum.enlem = liste[3]
        konum.boylam = liste[4]
        konum.sehir.adi = liste[5]
        konum.sehir.save()
        konum.ulke.adi = liste[6]
        konum.ulke.save()
        konum.save()
        return rc.ALL_OK
    def create(self,request):
        decoder = DecodeBase64()
        liste = decoder.returnParams(request.POST.get('params'))
        kullanici = Kullanici.objects.get(dogrulama_id = liste[0], email = liste[1],
                                            parola = liste[2])
        konum=Konum.objects.create(enlem = liste[3],
                boylam = liste[4], 
                sehir = Sehir.objects.create(adi = liste[5]),
                ulke=Ulke.objects.create(adi=liste[6]) )
        kullanici.konum = konum
        kullanici.save()
        return rc.CREATED
