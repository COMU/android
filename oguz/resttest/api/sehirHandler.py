#!/usr/bin/python
# -*- coding: utf-8 -*-

from piston.handler import BaseHandler
from piston.utils import rc,throttle
from myapp.models import *
from api.decodebase64 import *
class SehirHandler(BaseHandler):
    allowed_methods = ("GET", "POST", "PUT")

    def read(self,request):
        decoder = DecodeBase64()
        liste = []
        if request.GET.has_key('param'):
            liste = decoder.returnParams(request.GET.get('param'))
            print liste
        try:
            kullanici = Kullanici.objects.get(email = liste[0],
                                            parola = liste[1],
                                            dogrulama_id = liste[2],
                                            durum = True)
            if len(liste)==3:
                return kullanici.konum.sehir
            else:
                return Sehir.objects.filter(ulke=Ulke.objects.get(adi=liste[3]))
        except Kullanici.DoesNotExist:
            return -1

    def uptade(self, request, sehir_id):
        sehir = Sehir.objects.get(id=sehir_id)
        sehir.adi=request.PUT.get("sehir_adi")
        sehir.save()
        return rc.ALL_OK
    def create(self,request):
        sehir=Sehir.objects.create(adi=request.POST.get("sehir_adi"))
        return rc.CREATED
