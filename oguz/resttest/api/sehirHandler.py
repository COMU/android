#!/usr/bin/python
# -*- coding: utf-8 -*-

from piston.handler import BaseHandler
from piston.utils import rc,throttle
from myapp.models import *

class SehirHandler(BaseHandler):
    allowed_methods = ("GET", "POST", "PUT")

    def read(self,request,sehir_id):
        base= Sehir
        if(sehir_id):
            return base.objects.get(id=sehir_id)
        else:
            return base.objects.all()
    def uptade(self, request, sehir_id):
        sehir = Sehir.objects.get(id=sehir_id)
        sehir.adi=request.PUT.get("sehir_adi")
        sehir.save()
        return rc.ALL_OK
    def create(self,request):
        sehir=Sehir.objects.create(adi=request.POST.get("sehir_adi"))
        return rc.CREATED
