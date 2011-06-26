#!/usr/bin/python
# -*- coding: utf-8 -*-


from piston.handler import BaseHandler
from piston.utils import rc,throttle
from myapp.models import *
from api.decodebase64 import *
from api.hash import *
class KullaniciDetaylari(BaseHandler):
    allowed_methods = ("GET", "POST", "PUT")
    def read(self,request):
        decoder = DecodeBase64()
        liste = []
        if request.GET.hash_key("param"):
            liste=decoder.returnParams(request.GET.get('param'))
        try:
            kullanici = Kullanici.objects.get(email = liste[0], parola = liste[1],dogrulama_id = liste[2],durum = True)
            return kullanici.kullanici_detaylari
        except Kullanici.DoesNotExist:
            return -1


    def update(self,request):
         deceoder=DecodeBase64()
         liste=deceoder.returnParams(request.PUT.get('params'))
         egitim, created=EgitimDurumu.objects.get_or_create(adi=liste[4])
         meslek, created=Meslek.objects.get_or_create(adi=liste[5])
         kullanici = Kullanici.objects.get(dogrulama_id = liste[0], email = liste[1],parola = liste[2])
         kullanici_detaylari=kullanici.kullanici_detaylari
         kullanici_detaylari.cinsiyet=liste[3]
         kullanici_detaylari.egitim_durumu=egitim
         kullanici_detaylari.meslek=meslek
         kullanici_detaylari.meslek.save()
         kullanici_detaylari.egitim_durumu.save()
         kullanici_detaylari.save()


    def create(self,request):
        decoder=DecodeBase64()
        liste=decoder.returnParams(request.POST.get('params'))
        print liste
        kullanici=Kullanici.objects.get(dogrulama_id=liste[0])
        meslek, created=Meslek.objects.get_or_create(adi=liste[5])
        egitim, created=EgitimDurumu.objects.get_or_create(adi=liste[4])
        kul_detay=KullaniciDetaylari.objects.create(cinsiyet=liste[3],meslek=meslek,egitim_durumu=egitim)
        kul_detay.save()
        kullanici.kullanici_detaylari=kul_detay
        kullanici.save()
        return rc.CREATED
