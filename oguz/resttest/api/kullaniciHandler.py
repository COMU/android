#!/usr/bin/python
# -*- coding: utf-8 -*-
from piston.handler import BaseHandler
from piston.utils import rc,throttle
from myapp.models import *
from api.emailGonder import Email
from api.hash import Crypt
from api.decodebase64 import *
from PIL import Image
import base64
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
                    dogrulama_id = liste[2],
                    durum = True)
            return kullanici
        except Kullanici.DoesNotExist:
            return -1
    def update(self,request):
        decoder = DecodeBase64()
        liste  = decoder.returnParams(request.PUT.get('params'))

        print liste
        kullanici = Kullanici.objects.get(dogrulama_id = liste[0])
        kullanici.ad = liste[1]
        kullanici.soyad = liste[2]
        kullanici.dogum_tarihi = liste[4]
        kullanici.email = liste[3]
        # kullanici.parola = request.PUT.get("parola")
        kullanici.save()
        print "kaydetti."
        return rc.ALL_OK
    def create(self,request):
            decoder = DecodeBase64()
            params = decoder.returnParams(request.POST.get("params"))
            kullanici, created = Kullanici.objects.get_or_create(ad = params[0], soyad = params[1], email = params[2])
            if not created:
                return rc.CREATED
            else:
                kullanici.parola = params[3]
                kullanici.dogrulama_id = params[4]
                email = Email()
                hashMd5 = Crypt()
                email.gonder("Ahbap Onay Kodu", kullanici.parola, kullanici.dogrulama_id, kullanici.email )
                kullanici.parola = hashMd5.returnHash(kullanici.parola)
                kullanici.save()
                return rc.CREATED
        
