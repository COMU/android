#!/usr/bin/python
# -*- coding: utf-8 -*-
from piston.handler import BaseHandler
from piston.utils import rc,throttle
from myapp.models import *
from api.emailGonder import Email
from api.hash import Crypt
from api.decodebase64 import *
class KullaniciHandler(BaseHandler):
    allowed_methods = ("GET", "PUT", "POST")
    def read(self, request):
        decoder = DecodeBase64()
        liste = []
        
        if request.GET.has_key('param'):
            liste = decoder.returnParams(request.GET.get('param'))
            print liste
        try:
            kullanici = Kullanici.objects.get(email = liste[0],
                    parola = liste[1],
                    dogrulama_id = liste[2]
                    durum = True)
            return kullanici
        except Kullanici.DoesNotExist:
            return -1
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
        kullanici, created = Kullanici.objects.get_or_create(ad=request.POST.get("ad"),soyad=request.POST.get("soyad"),email=request.POST.get("email"))
        if not created:
            return rc.CREATED
        else:
            kullanici.parola = request.POST.get("parola")
            kullanici.dogrulama_id = request.POST.get("dogrulama_id")
            email = Email()
            hashMd5 = Crypt()
            email.gonder("Ahbap Onay Kodu", kullanici.parola, kullanici.dogrulama_id, kullanici.email )
            kullanici.parola = hashMd5.returnHash(kullanici.parola)
            kullanici.save()
        return rc.CREATED
        
