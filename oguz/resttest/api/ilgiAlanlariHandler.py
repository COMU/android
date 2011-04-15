#!/usr/bin/python
# -*- coding: utf-8 -*-

from piston.handler import BaseHandler
from piston.utils import rc,throttle
from myapp.model import IlgiAlanlari

class IlgiAlanlariHandler(BaseHandler):
    allowed_methods=("GET", "POST", "PUT")
    def read(self,request,ilgi_id = None):

        if(ilgi_id):
            return IlgiAlanlari.objects.get(id = ilgi_id)
        else:
            return IlgiAlanlari.objects.all()
    def uptade(self,request, ilgi_id):
        ilgi=IlgiAlanlari.objects.get(id = ilgi_id)
        #islemler
        ilgi.save()
        return rc.ALL_OK
    def create(self, request):
        IlgiAlanlari.objects.create(adi = request.POST.get("adi"))
        return rc.CREATED



