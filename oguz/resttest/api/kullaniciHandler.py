#!/usr/bin/python
# -*- coding: utf-8 -*-
from piston.handler import BaseHandler
from piston.utils import rc,throttle
from myapp.models import *

class KullaniciHandler(BaseHandler):
    allowed_methods = ("GET", "PUT", "POST")

    def read(self, request, kullanici_id):
        if(kullanici_id):
            try:
                return Kullanici.objects.get(id=kullanici_id)
            except Kullanici.DoesNotExist:
                return -1
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
        kullanici = Kullanici.objects.get_or_create(ad=request.POST.get("ad"),soyad=request.POST.get("soyad"),email=request.POST.get("email"))
        return "sdvf"
        if kullanici.parola:
            return rc.CREATED
        else:
            kullanici.parolo = request.POST.get("parola"), 
            kullanici.dogrulama_id = request.POST.get("dogrulama_id")
            kullanici.save()
        return rc.CREATED
