#!/usr/bin/python
# -*- coding: utf-8 -*-

from piston.handler import BaseHandler
from piston.utils import rc,throttle
from myapp.models import EgitimDurumu

class EgitimDurumuHandler(BaseHandler):
    allowed_methods = ("PUT", "GET", "POST")

    def read(self,request,egitim_id=None):
        
        if(egitim_id):
            return EgitimDurumu.objects.get(id=egitim_id)
        else:
            return EgitimDurumu.objects.all()
    
    def uptade(self,request,egitim_id):
        egitim_durumu = EgitimDurumu.objects.get(id = egitim_id)
        egitim_durumu.adi = request.PUT.get("egitim_durumu") 
        return rc.ALL_OK

    def create(self,request):
        EgitimDurumu.objects.create(adi=request.POST.get("egitim_durumu"))
        return rc.CREATED
