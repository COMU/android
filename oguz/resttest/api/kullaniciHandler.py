#!/usr/bin/python
# -*- coding: utf-8 -*-

from piston.handler import BaseHandler
from piston.utils import rc,throttle
from myapp.models import *

class KullaniciHandler(BaseHandler):
    allowed_methods = ("GET", "PUT", "POST")

    def read(self, request, kullanici_id):
        if(kullanici_id):
            return Kullanici.objects.get(id=kullanic_id)
        else:
            return Kullanici.objects.all()
    def uptade(self,request,kullanici_id):
        #ici bos
        return rc.ALL_OK
    def create(self,request):
        mKonum=Konum.objects.create(enlem=request.POST.get("enlem"),
                boylam=request.POST.get("boylam"),
                sehir=Sehir.objects.get(adi=request.POST.get("sehir_adi")),
                ulke=Ulke.objects.get(adi=request.POST.get("ulke_adi")))
        Kullanici.objects.create(ad=request.POST.get("ad"),
                soyad=request.POST.get("soyad"),dogum_tarihi=request.POST.get("dogum_tarihi"),email=request.POST.get("email"),parola=request.POST.get("parola"),konum=mKonum)
        return rc.CREATED
