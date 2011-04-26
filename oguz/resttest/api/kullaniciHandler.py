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
        kullanici = Kullanici.objects.get(id = kullanici_id)
        kullanici.adi = request.PUT.get("ad")
        kullanici.soyad = request.PUT.get("soyad")
        kullanici.dogum_tarihi = request.PUT.get("dogum_tarihi")
        kullanici.email = request.PUT.get("email")
        kullanici.parola = request.PUT.get("parola")
        kullanici.save()
        return rc.ALL_OK
    def create(self,request):
        Kullanici.objects.create(ad=request.POST.get("ad"),soyad=request.POST.get("soyad"),email=request.POST.get("email"))
        return rc.CREATED
