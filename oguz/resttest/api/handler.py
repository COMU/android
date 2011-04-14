#!/usr/bin/env python
#-*- coding: UTF-8 -*-

from piston.handler import BaseHandler
from piston.utils import rc, throttle
from myapp.models import *

class MeslekHandler(BaseHandler):
    allowed_methods = ('GET','PUT','DELETE','POST')
   # fields = ('meslek_adi')
    model = Meslek

    def read(self, request, meslek_id=None):

        #base = Meslek

       # if (meslek_id):
        #    return base.objects.get(id=meslek_id)
       # else:
        #    return base.objects.all()
        return Konum.objects.all()
   # @throttle(5, 10*6*)
    def update(self, request, meslek_id):
        meslek = Meslek.objects.get(id=meslek_id)

        meslek.adi = request.PUT.get('meslek_adi')
      #  return meslek.adi
        meslek.save()

        return rc.ALL_OK

    def delete(self, request, meslek_adi):
        post = Meslek.objects.get(adi=meslek_adi)
        post.delete()

        return rc.DELETED

    def create(self, request, meslek_adi):
        post = Meslek.objects.create(adi=meslek_adi)

        return rc.CREATED
