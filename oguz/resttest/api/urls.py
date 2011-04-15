#!/usr/bin/env python
#-*- coding: UTF-8 -*-

from django.conf.urls.defaults import *
from piston.resource import Resource
from handler import MeslekHandler
from sehirHandler import SehirHandler
from ulkeHandler import UlkeHandler
from konumHandler import KonumHandler
from kullaniciHandler import KullaniciHandler
from egitimDurumuHandler import EgitimDurumuHandler
from ilgiAlanlariHandler import IlgiAlanlariHandler

ilgi_handler = Resource(IlgiAlanlariHandler)
egitim_handler = Resource(EgitimDurumHandler)
kullanici_handler = Resource(KullaniciHandler)
meslek_handler = Resource(MeslekHandler)
sehir_handler = Resource(SehirHandler)
ulke_handler = Resource(UlkeHandler)
konum_handler = Resource(KonumHandler)
urlpatterns = patterns('',
     url(r'^ilgi/(?P<ilgi_id>[^/]+)', ilgi_handler),
     url(r'^ilgi/', ilgi_handler),
     url(r'^egitim/(?P<egitim_id>[^/]+)', egitim_handler),
     url(r'^egitim/', egitim_handler),
     url(r'^kullanici/(?P<kullanici_id>[^/]+)', kullanici_handler),
     url(r'^kullanici/', kullanici_handler),
     url(r'^konum/(?P<konum_id>[^/]+)', konum_handler),
     url(r'^konum/', konum_handler),
     url(r'^ulke/(?P<ulke_id>[^/]+)', ulke_handler),
     url(r'^ulke/', ulke_handler),
     url(r'^sehir/(?P<sehir_id>[^/]+)', sehir_handler),
     url(r'^sehir/', sehir_handler),
     url(r'^meslek/(?P<meslek_id>[^/]+)', meslek_handler),
     url(r'^meslekler/', meslek_handler)
)
