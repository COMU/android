#!/usr/bin/python
# -*- coding: utf-8 -*-

from piston.handler import BaseHandler
from piston.utils import rc,throttle
from myapp.models import Ulke

class UlkeHandler(BaseHandler):
    allowed_methods = ("GET","POST","PUT")

    def read(self,request,ulke_id):
        base=Ulke
        if(ulke_id):
            return Ulke.objects.get(id=ulke_id)
        else:
            return Ulke.objects.all()
    def uptade(self,request,ulke_id):
        ulke=Ulke.objects.get(id=ulke_id)
        ulke.adi=request.PUT.get("ulke_adi")
        ulke.save()
        return rc.ALL_OK
    def create(self,request):
        ulke=Ulke.objects.create(adi=request.POST.get("ulke_adi"))
        return rc.CREATED


