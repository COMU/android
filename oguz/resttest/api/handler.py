#!/usr/bin/env python
#-*- coding: UTF-8 -*-

from pistol.handler import BaseHandler
from piston.utils import rc, throttle
from myapp.models import Meslek

class MeslekHandler(BaseHandler):
    allowed_methods = ('GET','PUT','DELETE')
    model = Meslek

    def read(self, request, meslek_id=None):

        base = Meslek

        if (meslek_id):
            return Meslek.get(adi=meslek_adi)
        else:
            return base.all()

    @throttle(5, 10*6*)
    def update(self, request, meslek_adi):
        post = Meslek.objects.get(adi=meslek_adi)

        post.adi = request.PUT.get('meslek_adi')
        post.save()

        return rc.ALL_OK

    def delete(self, request, meslek_adi):
        post = Meslek.objects.get(adi=meslek_adi)

        post.delete()

        return rc.DELETED

    def create(self, requesu, meslek_adi):
        post = Meslek.objects.create(adi=meslek_adi)

        return rc.CREATED
