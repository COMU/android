#!/usr/bin/python
# -*- coding: utf-8 -*-

from piston.handler import BaseHandler
from piston.utils import rc,throttle
from myapp.models import *

class KonumHandler(BaseHandler):
    allowed_methods = ("GET", "POST", "PUT")

    def read(self,request,konum_id):
        base=Konum

        if(konum_id):
            return base.objects.get(id=konum_id)
        else:
            return base.objects.all()
    def uptade(self,request,konum_id):
        konum=Konum.objects.get(id=konum_id)
        return "Doldur...."
    def create(self,request):
        konum=Konum.objects.create(enlem=request.POST.get("enlem"),
                boylam=request.POST.get("boylam"), 
                sehir=Sehir.objects.get(adi=request.POST.get("sehir_adi")),
                ulke=Ulke.objects.get(adi=request.POST.get("ulke_adi")) )
        return rc.CREATED
