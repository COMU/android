#!/usr/bin/python
# -*- coding: utf-8 -*-

from piston.handler import BaseHandler
from piston.utils import rc,throttle
from myapp.models import *
from api.decodebase64 import *
class UlkeHandler(BaseHandler):
    allowed_methods = ("GET","POST","PUT")

    def read(self,request):
        decoder = DecodeBase64()
        liste = []

        if request.GET.has_key("param"):
            liste = decoder.returnParams(request.GET.get('param'))
        print liste
        try:
            kullanici = Kullanici.objects.get(email = liste[0],
                                            parola = liste[1],
                                            dogrulama_id = liste[2],
                                            durum = True)
            if len(liste)==3:
                return kullanici.konum.ulke
            else:
                return Ulke.objects.all()
        except Kullanici.DoesNotExist:
            return -1
    def uptade(self,request,ulke_id):
        ulke=Ulke.objects.get(id=ulke_id)
        ulke.adi=request.PUT.get("ulke_adi")
        ulke.save()
        return rc.ALL_OK
    def create(self,request):
        ulke=Ulke.objects.create(adi=request.POST.get("ulke_adi"))
        return rc.CREATED


